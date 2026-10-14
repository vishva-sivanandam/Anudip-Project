
package com.marriage.dao;

import com.marriage.model.Worker;
import com.marriage.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class WorkerDAO {

    // Save a new worker
    public void save(Worker worker) {

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(worker);

            transaction.commit();

        } catch (RuntimeException exception) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw exception;
        }
    }

    // Retrieve all workers
    public List<Worker> findAll() {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM Worker ORDER BY workerId ASC",
                    Worker.class
            ).list();
        }
    }

    // Retrieve one worker using the primary key
    public Worker findById(Integer workerId) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Worker.class, workerId);
        }
    }
}
