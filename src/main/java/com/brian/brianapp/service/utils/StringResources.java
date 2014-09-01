package com.brian.brianapp.service.utils;

import javax.ws.rs.GET;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ebrigun
 * Date: 01/09/14
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public class StringResources {



    /**
     *      terrible hard code quick fix soln!
     */



    private ArrayList<String> urls = new ArrayList();
    private static String url_1 = "GET {{Host}}/brian-webapp/rest/cache/start?cache={{brianCache}}";
    private static String url_2= "PUT {{Host}}/brian-webapp/rest/cache/set?ip=x.x.x.x";
    private static String url_3= "GET {{Host}}/brian-webapp/rest/cache/inspect?cache={{brianCache}}";
    private static String url_4= "GET {{Host}}/brian-webapp/rest/cache/get?cache={{brianCache}}&key=football";
    private static String url_5= "GET {{Host}}/brian-webapp/rest/cache/getall?cache={{brianCache}}";
    private static String url_6= "PUT {{Host}}/brian-webapp/rest/cache/put?cache={{brianCache}}&key=football&value=round";
    private static String url_7= "DELETE {{Host}}/brian-webapp/rest/cache/delete?cache={{brianCache}}&key=football";
    private static String url_8= "GET {{Host}}/brian-webapp/rest/cache/help";

    public StringResources(){
        init();
    }

    private void init() {
        urls.add(url_1);
        urls.add(url_2);
        urls.add(url_3);
        urls.add(url_4);
        urls.add(url_5);
        urls.add(url_6);
        urls.add(url_7);
        urls.add(url_8);
    }




    public ArrayList<String> getUrls(){

        return urls;

    }
}
