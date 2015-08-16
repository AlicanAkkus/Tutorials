package com.wora.cache;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * Created by caysever on 15.08.2015.
 */
public class OSCacheSingleton extends GeneralCacheAdministrator{

    private static OSCacheSingleton cacheAdmin = new OSCacheSingleton();

    private OSCacheSingleton(){

    }

    public static OSCacheSingleton getInstance(){
        if (cacheAdmin == null) {
            cacheAdmin = new OSCacheSingleton();
            return cacheAdmin;
        }

        return cacheAdmin;
    }

    public void putCache(String key, Object message){
        cacheAdmin.putCache(key, message);
    }

    public Object getCache(String key) throws NeedsRefreshException{
        return cacheAdmin.getCache(key);
    }
}
