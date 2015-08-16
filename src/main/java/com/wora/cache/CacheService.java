package com.wora.cache;

import com.opensymphony.oscache.base.NeedsRefreshException;
import org.apache.log4j.Logger;

/**
 * Created by caysever on 15.08.2015.
 */
public class CacheService{
    Logger logger = Logger.getLogger(CacheService.class);
    OSCacheSingleton cacheAdmin = OSCacheSingleton.getInstance();


    public void putMessageToCache(String key, Object message){
        try{
            cacheAdmin.putCache(key, message);
        }catch (Exception e){
            cacheAdmin.cancelUpdate(key);
        }
    }

    public Object getMessageFromCache(String key){
        try{
            return cacheAdmin.getCache(key);
        }catch (NeedsRefreshException nre){
            try{
                cacheAdmin.putCache(key, key);
                return cacheAdmin.getCache(key);
            }catch (Exception e){
                logger.error(e, e);
            }
        }

        return null;
    }
}
