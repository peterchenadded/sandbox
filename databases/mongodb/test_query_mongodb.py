# not working causes it to freeze
#from gevent import monkey
#monkey.patch_all()
import gc
import os
import psutil
#from guppy import hpy

from pymongo import MongoClient
import logging
import datetime
import time
import re
import ssl
import multiprocessing
import threading

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


def batch_find(i):
    new_client = get_client(multiprocessing.current_process().name)
    #logging.info('test_kvstore count = %s' % new_client.test.test_kvstore.count())
    query = {"correlation_id": {"$in": i}, "time_start": {"$gt": min(i)-1, "$lt": max(i)+1}, "is_valid": 1}
    cursor = new_client.test.test_kvstore.find(query)

    logging.info('found %s' % cursor.count())
    for r in cursor:
        logging.info(r)
        break
    logging.info('completed %s in pid %s' % (len(i), os.getpid()))

diff_results = []

def batch_query_rows(n):
    logging.info('batch query %s rows' % n)
    rows = []
    for i in range(0, n):
        rows.append(i)

    rows_chunked = []
    for i in chunks(rows, CHUNK_SIZE):
        rows_chunked.append(i)

    a = datetime.datetime.now()
    pool = multiprocessing.Pool(MAX_THREADS)
    pool.map(batch_find, rows_chunked, 1)
    pool.close()
    pool.join()

    b = datetime.datetime.now()
    diff = (b-a).total_seconds()
    logging.info('took %s seconds to query %s rows' % (diff, n))
    
    diff_results.append(diff)


tests = [100, 1000, 10000, 20000, 30000, 50000, 100000, 200000, 300000, 500000, 700000, 1000000]
#tests = [100]
for i in tests:
    batch_query_rows(i)

print diff_results

