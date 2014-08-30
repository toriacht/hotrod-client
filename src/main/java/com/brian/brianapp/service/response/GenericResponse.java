package com.brian.brianapp.service.response;

/**
 * Created with IntelliJ IDEA.
 * User: ebrigun
 * Date: 29/08/14
 * Time: 21:57
 * To change this template use File | Settings | File Templates.
 */
public class GenericResponse {

    private String key = null;
    private String value = null;

    public GenericResponse(String key, String value) {
        setKey(key);
        setValue(value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
