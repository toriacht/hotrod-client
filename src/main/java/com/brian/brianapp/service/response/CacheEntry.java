package com.brian.brianapp.service.response;

/**
 * Created with IntelliJ IDEA.
 * User: ebrigun
 * Date: 29/08/14
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */

/**
 * Proxy Object to return to UI
 */
public class CacheEntry {

    private Object key=null;

    private Object value=null;


    public CacheEntry(Object key, Object value) {
        setKey(key);
        setValue(value);
    }


    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CacheEntry{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
