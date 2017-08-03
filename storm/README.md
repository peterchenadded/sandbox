# Chapter 1

1. Apache Storm is a distributed, real-time computational framework for stream processing
2. Big data is best understand by considering the big 4 Vs:
    * volume (amount)
    * velocity (speed)
    * variety (various sources)
    * veracity (accuracy)
3. Big data tools:
    * data processing (Storm)
    * data transfer (e.g. Kafka, flume, Scribe)
    * data storage (HDFS, GlusterFS, NoSQL)

# Chapter 2

1. Stream use case - most active developers against any repository
    * A component to read the live feed of commits
    * A component that accepts the Email from the commit
    * A component that updates an in-memory map where the key is the email and the value is the number of commits for that email
2. Topoloy - a graph of computation where nodes represent computations and edges represent data being passed
3. Tuple - data is sent between nodes in the form of tuples (ordered list of values)
4. Stream - an unbounded sequence of tuples between two nodes in the topology
5. Spout - the source of a stream in the topology
6. Bolt - accepts a tuple from its input stream and performs some computation or transformation

Shuffle grouping - type of stream grouping where tuples are emitted to instances of bolts at random
Fields grouping - ensures that tuples with the same value for a particular field name are always emitted to the same instance of a bolt
