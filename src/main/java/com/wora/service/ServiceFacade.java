package com.wora.service;

import com.wora.db.DBHelper;
import com.wora.hibernate.SessionFactoryInitializer;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by caysever on 15.08.2015.
 */
public class ServiceFacade {
    private static ServiceFacade facade = new ServiceFacade();
    private DBHelper dbHelper;
    private SessionFactoryInitializer sessionFactory;
    private ServiceFacade(){

    }

    public static ServiceFacade getFacade() {
        return facade;
    }

    public void startService(Properties properties, ServletContext context){
       /* dbHelper = new DBHelper();
        dbHelper.initalize(properties);
*/
        sessionFactory = new SessionFactoryInitializer();
        sessionFactory.initalize(context);
    }

    public Connection getConnection(){
        return dbHelper.getConnection();
    }
}
