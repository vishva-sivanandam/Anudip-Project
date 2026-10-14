
package com.marriage.service;

import com.marriage.dao.WorkerDAO;
import com.marriage.dao.ServiceOfferingDAO;
import com.marriage.model.Worker;
import com.marriage.model.ServiceOffering;

public class WorkerService {

    private final WorkerDAO workerDAO = new WorkerDAO();
    private final ServiceOfferingDAO offeringDAO = new ServiceOfferingDAO();

    public void addWorkerAndOffering(
            Worker worker,
            ServiceOffering offering) {

        if (worker.getName() == null
                || worker.getName().trim().isEmpty()) {

            throw new IllegalArgumentException("Worker name is required.");
        }

        if (offering.getCustomerPrice() < 0) {

            throw new IllegalArgumentException("Service price cannot be negative.");
        }

        workerDAO.save(worker);

        offering.setWorker(worker);

        offeringDAO.save(offering);
    }
}
