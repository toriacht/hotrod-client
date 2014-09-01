package com.brian.brianapp.service.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ebrigun
 * Date: 29/08/14
 * Time: 21:57
 * To change this template use File | Settings | File Templates.
 */
public class GenericResponseList {

    private List<GenericResponse> urls =null;
    private String name;


    public GenericResponseList(ArrayList<GenericResponse> urls, String name) {
        setUrls(urls);
        setName(name);

    }

    public List<GenericResponse> getUrls() {
        return urls;
    }

    public void setUrls(List<GenericResponse> urls) {
        this.urls = urls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
