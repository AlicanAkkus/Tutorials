package com.wora.db;

import org.apache.log4j.Logger;
import org.postgresql.ds.PGPoolingDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by caysever on 15.08.2015.
 */
public class DBHelper {

    Logger logger = Logger.getLogger(DBHelper.class);

    public void initalize(Properties properties){
        logger.info("DBHelper is initializing");

        DBConstant.dbType = properties.getProperty("tutorials.db.type");
        DBConstant.dbDriver = properties.getProperty("tutorials.db.driver");
        DBConstant.dbUrl = properties.getProperty("tutorials.db.url");
        DBConstant.dbUsername = properties.getProperty("tutorails.db.username");
        DBConstant.dbPassword = properties.getProperty("tutorails.db.passoword");
        DBConstant.dbPool = Boolean.getBoolean(properties.getProperty("tutorails.db.pool"));

        try{
            if(DBConstant.dbPool)
                Class.forName(DBConstant.dbDriver);
        }catch (Exception e){
            logger.error(e, e);
        }

        logger.info("DBHelper is initialized");
    }

    public Connection getConnection(){
        if(DBConstant.dbPool){
            return getConnectionFromJDBC();
        }else{
            return getConnectionFromDS();
        }
    }

    public Connection getConnectionFromJDBC(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DBConstant.dbUrl, DBConstant.dbUsername, DBConstant.dbPassword);
        }catch (Exception e){
            logger.error(e, e);
        }

        return connection;
    }

    public Connection getConnectionFromDS(){
        Connection connection = null;
        try{
            PGPoolingDataSource source = new PGPoolingDataSource();
            source.setDataSourceName("A Data Source");
            source.setServerName("localhost");
            source.setDatabaseName("postgres");
            source.setUser("postgres");
            source.setPassword("512066");
            source.setMaxConnections(10);

            connection = source.getConnection();
        }catch (Exception e){
            logger.error(e, e);
        }

        return connection;
    }
}
