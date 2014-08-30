package com.brian.brianapp.connectors;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.VersionedValue;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Created with IntelliJ IDEA.
 * User: ebrigun
 * Date: 26/08/14
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */
@Singleton
@Startup
public class RemoteHotRodCacheConnector {


    //hardcoded default host, can be modified via resources
    private  String cacheIP = "10.224.23.148";
//    private  Configuration conf = null;
    private RemoteCacheManager manager =null;
    private
    java.util.logging.Logger logger = Logger.getLogger(RemoteHotRodCacheConnector.class.getName());

    @PostConstruct
    void initiate() {

        //attempt to automatically set up connection on start up
        //if this fails connection can be initiated using resources/cache/start
        logger.info("PostConstruct Phase..........intiiating connection....");
        initiateCacheConnection();
    }

    /**
     * Connect to remote cache  manager using hotrod protocol
     *
     */
    public void initiateCacheConnection() {
        logger.info("Initiating cache connection to default host, this can be modified using /rest/cache/setcache?ip=xxxx ...." + getCacheIP());
        Configuration conf = new
                ConfigurationBuilder().addServer().host(getCacheIP()).port(11222).build();
        logger.info("Constructed Configuration: " + conf);
        logger.info("Creating new manager.......: ");
        manager = new RemoteCacheManager(conf);
        logger.info("SUCCESS: Connected to RemoteCacheManager at {}" + getCacheIP());
        logger.info("Setting RemoteCacheManager service reference");
        setManager(manager);

    }

    public RemoteCacheManager getManager() {
        return manager;
    }

    private void setManager(RemoteCacheManager manager) {
        this.manager = manager;
    }


    public String getCacheIP() {
        return cacheIP;
    }

    public void setCacheIP(String CACHE_IP) {
       this.cacheIP = CACHE_IP;
    }

//    public boolean inspectCache(String cache) {
//
//        if (conf == null) {
//
//            logger.warning("cache connection has not being initiated yet. Please run  \"curl {HOST}:{PORT}/brian-webapp/resources/cache/start\" to initiate the connection");
//            return false;
//        } else {
//            RemoteCacheManager manager = new RemoteCacheManager(conf);
//            System.out.println("SUCCESS: Constructed manager: " + manager);
//            logger.info("SUCCESS: Connected to RemoteCacheManager at {}" + cacheIP);
//            logger.info("Retrieving caches at" + cacheIP);
//            RemoteCache defaultCache = manager.getCache(cache);
//            logger.info("Retrieved cache : " + defaultCache.getName());
//            logger.info("Is Cache Empty: " + defaultCache.isEmpty());
//            return defaultCache.isEmpty();
//        }
//       // return false;
//
//    }
//
//    public Object getKey(String cache, String key) {
//
//        Object returnMe = null;
//
//        if (conf == null) {
//
//            logger.warning("cache connection has not being initiated yet. Please run  \"curl {HOST}:{PORT}/brian-webapp/resources/cache/start\" to initiate the connection");
//            return null;
//        } else {
//            RemoteCacheManager manager = new RemoteCacheManager(conf);
//            System.out.println("SUCCESS: Constructed manager: " + manager);
//            logger.info("SUCCESS: Connected to RemoteCacheManager at {}" + cacheIP);
//            logger.info("Retrieving caches at" + cacheIP);
//            RemoteCache defaultCache = manager.getCache(cache);
//            logger.info("Retrieved cache : " + defaultCache.getName());
//            Object cacheEntry = defaultCache.get(key);
//
//            //  defaultCache.Entry
//
//
//            logger.info("retrieving object " + defaultCache.get(cacheEntry));
//            if (cacheEntry != null) {
//                logger.info("Returned opbj in instance of: " + cacheEntry.getClass().getName());
//            }
//            return cacheEntry;
//        }
//       // logger.info("Something bad happened! returning null...");
//      //  return returnMe;
//    }
//
//    public void put(String cache, String key, String value) {
//
//        if (conf == null) {
//
//            logger.warning("cache connection has not being initiated yet. Please run  \"curl {HOST}:{PORT}/brian-webapp/resources/cache/start\" to initiate the connection");
//            return;
//        } else {
//            RemoteCacheManager manager = new RemoteCacheManager(conf);
//            System.out.println("SUCCESS: Constructed manager: " + manager);
//            logger.info("SUCCESS: Connected to RemoteCacheManager at {}" + cacheIP);
//            logger.info("Retrieving caches at" + cacheIP);
//            RemoteCache<String, String> remoteCache = manager.getCache(cache);
//            logger.info("Retrieved cache : " + remoteCache.getName());
//            logger.info("Updating  cache ........with key=" + key + " and value=" + value);
//            remoteCache.put(key, value);
//            logger.info("Retrieving new versioned value from cache ........ " + cache);
//            VersionedValue valueBinary = remoteCache.getVersioned("key");
////            assert remoteCache.containsKey(key) ;
//            logger.info("cache contains new key " + key + " ?" + remoteCache.containsKey(key));
//
//            logger.info("Cache updated: " + key + "=" + value);
//        }
//
//    }
//
//    public int getEntries(String cache) {
//
//        int noOfEntries = 0;
//        if (conf == null) {
//
//            logger.warning("cache connection has not being initiated yet. Please run  \"curl {HOST}:{PORT}/brian-webapp/resources/cache/start\" to initiate the connection");
//            return 0;
//        } else {
//            RemoteCacheManager manager = new RemoteCacheManager(conf);
//
////            CacheContainer cacheContainer = (CacheContainer) new RemoteCacheManager(conf);
////            cacheContainer.
//
//            logger.info("SUCCESS: Connected to RemoteCacheManager at {}" + cacheIP);
//            logger.info("Retrieving caches at" + cacheIP);
//            RemoteCache remoteCache = manager.getCache(cache);
//            Map entries = remoteCache.getBulk();
//            noOfEntries = entries.size();
//            logger.info("Number of entries: " + noOfEntries);
//
//            int i = 1;
//            for (Object key : entries.keySet()) {
//                logger.info("Entry +" + i + " : " + entries.get(key));
//                i++;
//            }
//
//
//        }
//        return noOfEntries;
//    }
//
//    public Object delete(String cache, String key) {
//        if (conf == null) {
//
//            logger.warning("cache connection has not being initiated yet. Please run  \"curl {HOST}:{PORT}/brian-webapp/resources/cache/start\" to initiate the connection");
//            return null;
//        } else {
//            RemoteCacheManager manager = new RemoteCacheManager(conf);
//            logger.info("SUCCESS: Connected to RemoteCacheManager at {}" + cacheIP);
//            logger.info("Retrieving caches at" + cacheIP);
//            RemoteCache remoteCache = manager.getCache(cache);
//            logger.info("Removing key entry "+key);
//            remoteCache.remove(key);
//            logger.info("Key removed"+key);
//        }
//        return null;
//    }
}

