package com.ehcache;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ehcache.manager.CachedUserManager;
import com.ehcache.model.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @author <a href="mailto:psunil1278@gmail.com">Sunil Kumar</a>
 * @since 16/11/15
 */
public class UserEhcacheDemo {


    public static final String CACHE_1 = "cache1";
    public static final String CACHE_2 = "cache2";

    public static void main(String[] args) {

        CacheManager cacheManager = buildCacheManager();
        Cache cache1 = getCache1(cacheManager);
        Cache cache2 = getCache2(cacheManager);
        final CachedUserManager cachedUserManager1 = new CachedUserManager<Integer, User>(cache1);
        final CachedUserManager cachedUserManager2 = new CachedUserManager<Integer, User>(cache2);

        int noOfThreads = 2;
        ExecutorService execService = Executors.newFixedThreadPool(noOfThreads);
        execService.submit(new Runnable() {
            String threadName = "thread_1";
            @Override
            public void run() {
                //adding some users to cache1
                addUsersToCache(cachedUserManager1,threadName);

                //retrieve users from cache1
                Integer i = new Integer(1);
                while(i<5)
                {
                    //any random value between 1 to 30 sec
                    int sleepTime = getRandomSleepTime(1000, 30000);
                    System.out.println(threadName +" will sleep during "+sleepTime+" milliseconds");
                    try {
                        Thread.currentThread().sleep(sleepTime);
                    }catch (InterruptedException e)
                    {
                        //do nothing
                    }
                    boolean exist = cachedUserManager1.keyExist(i);
                    System.out.println("user"+i+ (exist?" exist":" not exist")+ " in cache1 with "+threadName);
                    i++;
                }


            }
        });
        execService.submit(new Runnable() {
            String threadName = "thread_2";
            @Override
            public void run() {
                //adding some users to cache2
                addUsersToCache(cachedUserManager2,threadName);

                //retrieve users from cache1
                Integer i = new Integer(1);
                while(i<5)
                {
                    //any random value between 30 to 60 sec
                    int sleepTime = getRandomSleepTime(30000, 60000);
                    System.out.println(threadName +" will sleep during "+sleepTime+" milliseconds");
                    try {
                        Thread.currentThread().sleep(sleepTime);
                    }catch (InterruptedException e)
                    {
                        //do nothing
                    }
                    boolean exist = cachedUserManager2.keyExist(i);
                    System.out.println("user"+i+ (exist?" exist ":" not exist ")+ "in cache2 with "+threadName);
                    i++;
                }
            }
        });

    }

    public static Cache getCache1(CacheManager cacheManager) {
        return cacheManager.getCache(CACHE_1);
    }

    public static Cache getCache2(CacheManager cacheManager) {
        return cacheManager.getCache(CACHE_2);
    }

    public static CacheManager buildCacheManager() {
        InputStream is = UserEhcacheDemo.class.getClassLoader().getResourceAsStream("com/ehcache/userCache.xml");
        return CacheManager.create(is);
    }

    private static void addUsersToCache(CachedUserManager cachedUserManager,String threadName) {
        Integer i = new Integer(1);
        while(i<5){
            cachedUserManager.add(i, new User(i, "user" + i, "password" + i, "role" + i, i));
            System.out.println("Added user"+ i + " to "+ cachedUserManager.getCache().getName() + " using " + threadName);
            i++;
        }
    }

    private static int getRandomSleepTime(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }

}
