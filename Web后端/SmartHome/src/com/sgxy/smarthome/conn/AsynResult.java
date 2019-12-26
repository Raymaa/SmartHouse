package com.sgxy.smarthome.conn;

import javax.servlet.AsyncContext;

public class AsynResult {
    private AsyncContext context;

    private String userId;

    public AsyncContext getContext() {
        return context;
    }


    public String getUserId() {
        return userId;
    }

    public AsynResult(AsyncContext context,  String userId) {
        this.context = context;
 
        this.userId = userId;
    }


	
   
}
