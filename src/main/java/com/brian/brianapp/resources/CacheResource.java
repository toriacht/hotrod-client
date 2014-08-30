package com.brian.brianapp.resources;


import com.brian.brianapp.service.HotRodCacheService;
import com.brian.brianapp.service.response.Cache;
import com.brian.brianapp.service.response.CacheEntry;
import com.brian.brianapp.service.response.GenericResponse;
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
    public Response start() {
        logger.info("POJO Initiating cache connection...." + service.getCacheConnector().getCacheIP());
        GenericResponse resp = service.initiateCacheConnection();
        logger.info("POJO cache connection over ...." );
        Response.ResponseBuilder response = Response.ok(resp);
        return response.build();
    }


    @PUT
    @Path("/put")
    public Response put(@QueryParam("cache") String cache,@QueryParam("key") String key,
                      @QueryParam("value") String value) {
        logger.info("Inserting "+ key +"="+value+" into cache: "+cache);
        GenericResponse response = service.put(cache, key, value);
        logger.info("Cache Updated");
        Response.ResponseBuilder response1 = Response.ok(response);
        return response1.build();
    }

    @GET
    @Path("/inspect")
    public Response inspect(@QueryParam("cache") String cache) {
        logger.info("Inspecting cache:  "+cache);
        GenericResponse message = service.inspectCache(cache);
        logger.info("Cache inspected: "+cache);
        Response.ResponseBuilder response = Response.ok(message);
        return response.build();
    }


    @GET
    @Path("/get")
    public Response get(@QueryParam("cache") String cache,@QueryParam("key") String key) {
        logger.info("Retrieving key from  cache:  "+cache);
        CacheEntry entry = service.getKey(cache, key);
        logger.info("Cache queried for : "+key);
        Response.ResponseBuilder response = Response.ok(entry);
        return response.build();
    }


    @DELETE
    @Path("/delete")
    public Response delete(@QueryParam("cache") String cache,@QueryParam("key") String key) {
        logger.info("Deleting key from  cache:  "+cache);
        GenericResponse resp = service.delete(cache, key);
        Response.ResponseBuilder response = Response.ok(resp);
        return response.build();
    }


    @GET
    @Path("/getall")
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
    public Response set(@QueryParam("ip") String ip) {
        logger.info("Setting Cache " +ip);
        GenericResponse response = service.setCache(ip)  ;
        logger.info("Cache location Updated");
        Response.ResponseBuilder response1 = Response.ok(response);
        return response1.build();
    }

    @GET
    @Path("/help")
    public String help() {
        logger.info("\nPrinting available interfaces....:  ");
        String info = "\n**********************************************************************";
        String info1 = "\n ************* All commands are GET :(  ************************";
        String info3 = "\n**********************************************************************";
        String info4 ="\n  **************  Available interfaces @ /rest/cache  ********************* " ;
        String info5 = "\n 1. start";
        String info6 = "\n 2. inspect";
        String info7 = "\n 3. put";
        String info8 = "\n 4. get";
        String info9 = "\n 5. getall";
        String info10 = "\n 6. delete\n";
        String info2 = "\n\n ******** Sample command:  ***************** \n *********** curl -X GET \"IP_ADDRESS:8080/brian-webapp/rest/cache/put?cache=brianCache&key=cat&value=tiger\" *********\n";


        return info+info1+info3+info4+info5+info6+info7+info8+info9+info10+info2;
       // return helptext;
    }
}