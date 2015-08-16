package com.wora.restful;

import javax.ws.rs.core.MediaType;
import com.wora.bean.Person;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by caysever on 16.08.2015.
 */
@Path("/person")
public class PersonRest {
    @Context
    private ServletContext context;

    Logger logger = Logger.getLogger(PersonRest.class);

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Person> getPersonList(){
        logger.info("getPersonList method is started");

        ArrayList<Person> personList = new ArrayList<Person>();
        final SessionFactory factory = (SessionFactory) context.getAttribute("sessionFactory");
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();

        try{
            List list = session.createQuery("FROM Person ").list();
            Iterator<Person> personIterator = list.iterator();

            while (personIterator.hasNext()){
                Person person = personIterator.next();
                personList.add(person);
            }

        }catch (Exception e){
            logger.error(e, e);
        }finally {
            session.close();
        }

        logger.debug("Person list size : "  + personList.size());

        logger.info("getPersonList method is finished");
        return personList;
    }
}
