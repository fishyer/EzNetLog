package com.ezbuy.eznetlog;

import java.util.Map;

/**
 * author : yutianran
 * time   : 2019/01/18
 * desc   :
 * version: 1.0
 */
public class HttpModal {

    public String url;
    public String methodName;
    public Map<String, Object> args;
    public Map<String, String> header;
    public Object response;
    public int apiType;

    public HttpModal() {
    }
}
