package com.wora.servlet;

import com.wora.bean.Person;
import com.wora.service.ServiceFacade;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by caysever on 15.08.2015.
 */
@WebServlet(name = "DBTestServlet", urlPatterns = "/DBTest")
public class DBTestServlet extends HttpServlet {

    Logger logger = Logger.getLogger(DBTestServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Connection connection = ServiceFacade.getFacade().getConnection();

        ServletContext context = getServletContext();
        SessionFactory factory = (SessionFactory)context.getAttribute("sessionFactory");

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Person person = new Person();
        person.setName("Alican");
        person.setSurname("Akkus");
        person.setGender("male");

        try{
            session.save(person);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
            logger.error(e, e);
        }finally {
            session.close();
        }
    }
}
