package com.wora.servlet; /**
 * Created by caysever on 15.08.2015.
 */

import com.wora.service.ServiceFacade;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class LifeCycleListener implements ServletContextListener{
    Logger logger = Logger.getLogger(LifeCycleListener.class);

    public void contextInitialized(ServletContextEvent sce) {
        logger.info("contextInitialized method is started");

        try{

            String appPropsPath = sce.getServletContext().getRealPath("\\WEB-INF\\classes\\Tutorials.properties");
            Properties properties = createPropsFile(appPropsPath);

            ServiceFacade.getFacade().startService(properties, sce.getServletContext());

        }catch (Exception e){
            logger.error(e, e);
        }

        logger.info("contextInitialized method is finished");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("contextDestroyed method is started");

        logger.info("contextDestroyed method is finished");
    }

    public Properties createPropsFile(String path) throws Exception{
        logger.debug("Property path : " + path);
        File file = new File(path);

        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(file);
        properties.load(fileInputStream);

        return properties;
    }
}
