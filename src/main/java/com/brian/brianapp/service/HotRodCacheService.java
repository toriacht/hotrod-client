package com.brian.brianapp.service;

import com.brian.brianapp.connectors.RemoteHotRodCacheConnector;
import com.brian.brianapp.service.response.Cache;
import com.brian.brianapp.service.response.CacheEntry;
import com.brian.brianapp.service.response.GenericResponse;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.VersionedValue;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
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
public class HotRodCacheService {


    private final
    Logger logger = Logger.getLogger(HotRodCacheService.class.getName());


    @Inject
    private RemoteHotRodCacheConnector cacheConnector;


    public GenericResponse initiateCacheConnection() {
        logger.info("Initiating cache connection...." );
        cacheConnector.initiateCacheConnection();
        return new GenericResponse("Cache Contactable", "True");

    }

    public GenericResponse inspectCache(String cache) {

        RemoteCacheManager manager = cacheConnector.getManager();

        if (manager == null) {
            logger.warning("cache connection has not being initiated yet. Please run  \"curl {HOST}:{PORT}/brian-webapp/rest/cache/start\" to initiate the connection");
            return new GenericResponse("Cache Contactable", "False");
        } else {

            logger.info("SUCCESS: Connected to RemoteCacheManager at {}" + cacheConnector.getCacheIP());
            logger.info("Retrieving caches at" + cacheConnector.getCacheIP());
            RemoteCache defaultCache = manager.getCache(cache);
            logger.info("Retrieved cache : " + defaultCache.getName());
            logger.info("Is Cache Empty: " + defaultCache.isEmpty());
            return new GenericResponse("Cache Empty", "False");
        }
       // return false;

    }

    public CacheEntry getKey(String cache, String key) {

        Object returnMe = null;

        RemoteCacheManager manager = cacheConnector.getManager();

        if (manager == null) {

            logger.warning("cache connection has not being initiated yet. Please run  \"curl {HOST}:{PORT}/brian-webapp/rest/cache/start\" to initiate the connection");
            return null;
        } else {

            logger.info("Retrieving caches at" + cacheConnector.getCacheIP());
            RemoteCache defaultCache = manager.getCache(cache);
            logger.info("Retrieved cache : " + defaultCache.getName());
            logger.info("retrieving object " + key);
            Object cachedEntry = defaultCache.get(key);
            if (cachedEntry != null) {
                logger.info("Returned opbj in instance of: " + cachedEntry.getClass().getName());
            }
            CacheEntry entry = new CacheEntry(key,cachedEntry);
            return entry;
        }

    }

    public GenericResponse put(String cache, String key, String value) {

        RemoteCacheManager manager = cacheConnector.getManager();

        if (manager == null) {

            logger.warning("cache connection has not being initiated yet. Please run  \"curl {HOST}:{PORT}/brian-webapp/rest/cache/start\" to initiate the connection");

        } else {
            System.out.println("SUCCESS: Constructed manager: " + manager);
            logger.info("SUCCESS: Connected to RemoteCacheManager at {}" + cacheConnector.getCacheIP());
            logger.info("Retrieving caches at" + cacheConnector.getCacheIP());
            RemoteCache<String, String> remoteCache = manager.getCache(cache);
            logger.info("Retrieved cache : " + remoteCache.getName());
            logger.info("Updating  cache ........with key=" + key + " and value=" + value);
            remoteCache.put(key, value);
            logger.info("Retrieving new versioned value from cache ........ " + cache);
            logger.info("cache contains new key " + key + " ?" + remoteCache.containsKey(key));
            logger.info("Cache updated: " + key + "=" + value);
            return new GenericResponse("Entry "+key+" Added", "True");
        }
        return new GenericResponse("Entry "+key+" Added", "False");
    }

    public Cache getEntries(String cache) {

        int noOfEntries = 0;
        RemoteCacheManager manager = cacheConnector.getManager();
        ArrayList<CacheEntry> cacheEntries = new ArrayList();

        Cache retrievedCache;
        if (manager == null) {

            logger.warning("cache connection has not being initiated yet. Please run  \"curl {HOST}:{PORT}/brian-webapp/rest/cache/start\" to initiate the connection");
            return null;

        } else {

            logger.info("SUCCESS: Connected to RemoteCacheManager at {}" + cacheConnector.getCacheIP());
            logger.info("Retrieving caches at" + cacheConnector.getCacheIP());
            RemoteCache remoteCache = manager.getCache(cache);
            Map entries = remoteCache.getBulk();
            noOfEntries = entries.size();
            logger.info("Number of entries: " + noOfEntries);
            CacheEntry entry;

            int i = 1;
            for (Object key : entries.keySet()) {
                logger.info("Entry +" + i + " : " + entries.get(key));
                entry = new CacheEntry(key, entries.get(key));
                cacheEntries.add(entry);
                i++;
            }
            retrievedCache = new Cache(cacheEntries, remoteCache.getName());

        }
        return retrievedCache;
    }

    public GenericResponse delete(String cache, String key) {

        RemoteCacheManager manager = cacheConnector.getManager();

        if (manager == null) {

            logger.warning("cache connection has not being initiated yet. Please run  \"curl {HOST}:{PORT}/brian-webapp/rest/cache/start\" to initiate the connection");
            return new GenericResponse("Entry "+key+" Deleted", "False");
        } else {
            logger.info("SUCCESS: Connected to RemoteCacheManager at {}" + cacheConnector.getCacheIP());
            logger.info("Retrieving caches at" + cacheConnector.getCacheIP());
            RemoteCache remoteCache = manager.getCache(cache);
            logger.info("Removing key entry "+key);
            remoteCache.remove(key);
            logger.info("Key removed"+key);
            return new GenericResponse("Entry "+key+" Deleted", "True");
        }
//        return new GenericResponse("Entry "+key+" Deleted", "False");
    }

    public RemoteHotRodCacheConnector getCacheConnector() {
        return cacheConnector;
    }

    public GenericResponse setCache(String cacheIP) {
        logger.info("Setting new cache IP Address");
        cacheConnector.setCacheIP(cacheIP);
        logger.info("Initiating connection to new cache ip address");
        cacheConnector.initiateCacheConnection();
        logger.info("SUCCESS: new cache location set");
        return new GenericResponse("New Cache Connection to "+cacheIP+" Initiated", "True");
    }
}

