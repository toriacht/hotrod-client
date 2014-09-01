package com.brian.brianapp.resources;


import com.brian.brianapp.service.HotRodCacheService;
import com.brian.brianapp.service.response.Cache;
import com.brian.brianapp.service.response.CacheEntry;
import com.brian.brianapp.service.response.GenericResponse;
import com.brian.brianapp.service.response.GenericResponseList;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiVerb;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.logging.Logger;


/**
 * Created with IntelliJ IDEA.
 * User: ebrigun
 * Date: 27/08/14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
@Api(name = "Manage Your Remote Cache", description = "REST API to Manange a RemoteCacheManager via HotRod protocol")
@Path("/cache")
public class CacheResource {

    private final
    java.util.logging.Logger logger = Logger.getLogger(CacheResource.class.getName()) ;


    @Inject
    HotRodCacheService service;
    private String name;

    @GET
    @Path("/start")
    @Produces("application/json")
    public Response start() {
        logger.info("POJO Initiating cache connection...." + service.getCacheConnector().getCacheIP());
        GenericResponse resp = service.initiateCacheConnection();
        logger.info("POJO cache connection over ...." );
        Response.ResponseBuilder response = Response.ok(resp);
        return response.build();
    }


    @PUT
    @Path("/put")
    @Produces("application/json")
    public Response put(@QueryParam("cache") String cache,@QueryParam("key") String key,
                      @QueryParam("value") String value) {
        logger.info("Inserting "+ key +"="+value+" into cache: "+cache);
        Object response = service.put(cache, key, value);
        logger.info("Cache Updated");
        Response.ResponseBuilder response1 = Response.ok(response);
        return response1.build();
    }

    @GET
    @Path("/inspect")
    @Produces("application/json")
    public Response inspect(@QueryParam("cache") String cache) {
        logger.info("Inspecting cache:  "+cache);
        GenericResponse message = service.inspectCache(cache);
        logger.info("Cache inspected: "+cache);
        Response.ResponseBuilder response = Response.ok(message);
        return response.build();
    }


    @GET
    @Path("/get")
    @Produces("application/json")
    public Response get(@QueryParam("cache") String cache,@QueryParam("key") String key) {
        logger.info("Retrieving key from  cache:  "+cache);
        CacheEntry entry = service.getKey(cache, key);
        logger.info("Cache queried for : "+key);
        Response.ResponseBuilder response = Response.ok(entry);
        return response.build();
    }


    @DELETE
    @Path("/delete")
    @Produces("application/json")
    public Response delete(@QueryParam("cache") String cache,@QueryParam("key") String key) {
        logger.info("Deleting key from  cache:  "+cache);
        GenericResponse resp = service.delete(cache, key);
        Response.ResponseBuilder response = Response.ok(resp);
        return response.build();
    }


    @GET
    @Path("/getall")
    @Produces("application/json")
    public Response getall(@QueryParam("cache") String cache) {
        logger.info("Retrieving cache entries....:  "+cache);
        Cache returnCache = service.getEntries(cache);
        logger.info("Cache inspected: "+cache);

        Response.ResponseBuilder response = Response.ok(returnCache);
        return response.build();
        //return "Number of entries in cache: " +noOfEntries;
    }

    @PUT
    @Path("/set")
    @Produces("application/json")
    public Response set(@QueryParam("ip") String ip) {
        logger.info("Setting Cache " +ip);
        GenericResponse response = service.setCache(ip)  ;
        logger.info("Cache location Updated");
        Response.ResponseBuilder response1 = Response.ok(response);
        return response1.build();
    }

    @GET
    @Path("/help")
    @Produces("application/json")
    public Response help() {

        logger.info("Getting Help...");
        GenericResponseList response = service.help()  ;
        logger.info("\nReturning available interfaces....:  ");
        String info = "\n**********************************************************************";
        Response.ResponseBuilder response1 = Response.ok(response);
        return response1.build();
    }
}