package com.marriage.dao;

import com.marriage.model.Booking;
import com.marriage.model.BookingItem;
import com.marriage.model.Customer;
import com.marriage.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class BookingDAO {

    public Integer saveBooking(
            Customer customer,
            Booking booking,
            List<BookingItem> items) {

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

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

    // Check whether this worker already has a confirmed booking
    // on the selected marriage date.
    public boolean isWorkerBooked(Integer workerId, LocalDate date) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                            "SELECT COUNT(bi) " +
                                    "FROM BookingItem bi " +
                                    "WHERE bi.offering.worker.workerId = :workerId " +
                                    "AND bi.booking.marriageDate = :date " +
                                    "AND bi.booking.status = :status",
                            Long.class)
                    .setParameter("workerId", workerId)
                    .setParameter("date", date)
                    .setParameter("status", "CONFIRMED")
                    .getSingleResult();

            return count > 0;
        }
    }
}