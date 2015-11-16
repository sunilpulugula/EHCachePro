# EHCachePro
Stand alone program explains how to use Ehcache.


Ehcache is an open-source, standards-based cache for boosting performance, offloading your database, and simplifying scalability. As a robust, proven, and full-featured solution, it is today’s most widely used Java-based cache. You can use Ehcache as a general-purpose cache or a second-level cache for Hibernate. 

Dependencies requied to use Ehcache :

        <dependency>
            <groupId>net.sf.ehcache</groupId>
            <artifactId>ehcache</artifactId>
            <version>2.10.0</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.5.6</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-jdk14</artifactId>
            <version>1.5.6</version>
        </dependency>
        

A simple Cache xml configuration :

 <cache name="cache1"
           maxEntriesLocalHeap="1000"
           maxEntriesLocalDisk="10000"
           eternal="false"
           diskSpoolBufferSizeMB="20"
           timeToIdleSeconds="60"
           timeToLiveSeconds="300"
           memoryStoreEvictionPolicy="LFU"
           transactionalMode="off">
        <persistence strategy="localTempSwap"/>
    </cache>

1) maxEntriesLocalHeap : Maximum no of elements allowed in memory.
2) maxEntriesLocalDisk : Once Heap is full,then element overflow to disk memory.So this property is maximum no of elements allowed in Disk.
3) eternal : Sets whether elements are eternal. If eternal,  timeouts are ignored and the element is never expired.
4) diskSpoolBufferSizeMB : This is the size to allocate the DiskStore for a spool buffer. Writes are made
    to this area and then asynchronously written to disk. The default size is 30MB.
5) timeToIdleSeconds : Element will expire if idle time reachs to timeToIdleSeconds(in seconds).
6) timeToLiveSeconds : Element will expire if life of the element reachs to timeToIdleSeconds(in seconds).
7) memoryStoreEvictionPolicy : Policy would be enforced upon reaching the maxEntriesLocalHeap limit. Default policy is Least Recently Used (specified as LRU). Other policies available - First In First Out (specified as FIFO) and Less Frequently Used (specified as LFU)
8) transactionalMode :  To enable an ehcache as transactions, set the transactionalMode.
9) diskStore : If heap memory is full,then elements overflow to disk.Diskstore is the path of disk directory.
