package com.wora.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * Created by caysever on 16.08.2015.
 */
public class SessionFactoryInitializer {
    Logger  logger = Logger.getLogger(SessionFactoryInitializer.class);
    SessionFactory factory;

    public void initalize(ServletContext context){
        logger.info("SessionFactoryInitializer initalizing");


        try{
            factory = new Configuration().configure().buildSessionFactory();
            context.setAttribute("sessionFactory", factory);
        }catch (Exception e){
            logger.error(e, e);
        }

        logger.info("SessionFactoryInitializer initialized");
    }

}
