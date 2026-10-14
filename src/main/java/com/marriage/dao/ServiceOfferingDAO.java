package com.marriage.dao;

import com.marriage.model.ServiceOffering;
import com.marriage.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ServiceOfferingDAO {

    // Save a new service offering
    public void save(ServiceOffering offering) {

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(offering);

            transaction.commit();

        } catch (RuntimeException exception) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw exception;
        }
    }

    // Display all service offerings, from low to high price
    public List<ServiceOffering> findAll() {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM ServiceOffering ORDER BY customerPrice ASC",
                    ServiceOffering.class
            ).list();
        }
    }

    // Check whether the same worker already has this service and package
    public boolean existsOffering(
            Integer workerId,
            String serviceName,
            String packageName) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                            "SELECT COUNT(s) FROM ServiceOffering s " +
                                    "WHERE s.worker.workerId = :workerId " +
                                    "AND LOWER(s.serviceName) = LOWER(:serviceName) " +
                                    "AND LOWER(s.packageName) = LOWER(:packageName)",
                            Long.class
                    )
                    .setParameter("workerId", workerId)
                    .setParameter("serviceName", serviceName.trim())
                    .setParameter("packageName", packageName.trim())
                    .uniqueResult();

            return count != null && count > 0;
        }
    }

    // Find a selected service within the customer's budget
    public List<ServiceOffering> findByServiceAndBudget(
            String selectedService,
            double budget) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM ServiceOffering s " +
                                    "WHERE (LOWER(s.serviceName) = LOWER(:service) " +
                                    "OR LOWER(s.category) = LOWER(:service)) " +
                                    "AND s.customerPrice <= :budget " +
                                    "ORDER BY s.customerPrice ASC",
                            ServiceOffering.class
                    )
                    .setParameter("service", selectedService.trim())
                    .setParameter("budget", budget)
                    .list();
        }
    }
}
