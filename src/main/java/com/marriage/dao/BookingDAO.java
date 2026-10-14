
package com.marriage.dao;

import com.marriage.model.Booking;
import com.marriage.model.BookingItem;
import com.marriage.model.Customer;
import com.marriage.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookingDAO {

    public Integer saveBooking(
            Customer customer,
            Booking booking,
            List<BookingItem> items) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(customer);

            booking.setCustomer(customer);
            session.persist(booking);

            for (BookingItem item : items) {
                item.setBooking(booking);
                session.persist(item);
            }

            transaction.commit();

            return booking.getBookingId();

        } catch (RuntimeException exception) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw exception;
        }
    }
}
