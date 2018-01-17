# not working causes it to freeze
#from gevent import monkey
#monkey.patch_all()
import gc
import os
import psutil
#from guppy import hpy

import pymongo
from pymongo import MongoClient
import logging
import datetime
import time
import re
import ssl
import threading
import multiprocessing

logging.basicConfig(level=logging.INFO, format='%(asctime)s | %(levelname)s | %(process)d | %(message)s')

CHUNK_SIZE = 10000
MAX_THREADS = 4
USERNAME = 'root'
PASSWORD = 'letmein!'

clients_cache = {}
lock = threading.RLock()

def chunks(l, n):
    for i in xrange(0, len(l), n):
        yield l[i:i + n]

def get_client(pid):
    with lock:
        if not pid in clients_cache:
            client = MongoClient(ssl=True, username=USERNAME, password=PASSWORD, ssl_cert_reqs=ssl.CERT_NONE)
            clients_cache[pid] = client
    return clients_cache[pid]

def delete_rows():
    client = get_client(0)
    client.test.test_kvstore.drop()
    client.test.test_kvstore.create_index("correlation_id")
    client.test.test_kvstore.create_index([("time_start", pymongo.DESCENDING), ("is_valid", pymongo.ASCENDING)])

def batch_save(i):
    new_client = get_client(multiprocessing.current_process().name)
    new_client.test.test_kvstore.insert_many(i)
    logging.info('completed %s in pid %s' % (len(i), os.getpid()))

diff_results = []
def batch_insert_rows(n, delete=True):
    logging.info('batch inserting %s rows' %n)
    rows = []

    if delete:
        delete_rows()
        time.sleep(5)

    a = datetime.datetime.now()
    for i in xrange( n):
        time_start =  i
        time_end = time_start
        rows.append({"correlation_id": [i, i+1, i+2, i+3, i+4], "time_start": time_start, "time_end": time_end, "is_valid": 1, "environment": "E2E2", "app_name": "app1", "app_name_status": "app1#INFO", "service_name": "service_name1", "service_name_status": "service_name1#INFO", "requested_by": "requested_by1", "received_by": "received_by1,received_by2,received_by3", "status": "INFO", "customer_id": "customer_id1234567890"})
    b = datetime.datetime.now()
    logging.info('took %s seconds to generate rows' % (b-a).total_seconds())

    pool = multiprocessing.Pool(MAX_THREADS)
    a = datetime.datetime.now()
    rows_chunked = []
    for i in chunks(rows, CHUNK_SIZE):
        rows_chunked.append(i)
        #collection.data.batch_save(*i)
        #logging.info('batch saved %s rows' % len(i))

    #h = hpy()
    #logging.info('heap %s' % h.heap())

    pool.map(batch_save, rows_chunked, 1)
    pool.close()
    pool.join()

    b = datetime.datetime.now()
    diff = (b-a).total_seconds()
    logging.info('took %s seconds to bulk save %s rows' % (diff, n))

    diff_results.append(diff)

tests = [100, 1000, 10000, 20000, 30000, 50000, 100000, 200000, 300000, 500000, 700000, 1000000]
#tests = [1000000]
for i in tests:
    batch_insert_rows(i)

print diff_results

