package com.brian.brianapp.service.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ebrigun
 * Date: 29/08/14
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */

/**
 * Proxy Object to return to UI
 */
public class Cache {

    private String name = null;
    private int size = 0;
    private List<CacheEntry> entries =null;

    public Cache(ArrayList<CacheEntry> cacheEntries, String name) {
        setEntries(cacheEntries);
        setSize(cacheEntries.size());
        setName(name);
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public List<CacheEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<CacheEntry> entries) {
        this.entries = entries;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Cache{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", entries=" + entries +
                '}';
    }
}
