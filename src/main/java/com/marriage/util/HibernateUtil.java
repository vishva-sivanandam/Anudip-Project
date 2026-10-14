package com.marriage.util;

import com.marriage.model.Booking;
import com.marriage.model.BookingItem;
import com.marriage.model.Customer;
import com.marriage.model.ServiceOffering;
import com.marriage.model.Worker;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Worker.class)
                    .addAnnotatedClass(ServiceOffering.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Booking.class)
                    .addAnnotatedClass(BookingItem.class)
                    .buildSessionFactory();
        } catch (Exception exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
    public static void shutdown() {
        SESSION_FACTORY.close();
    }
}