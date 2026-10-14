package com.marriage.service;

import com.marriage.dao.ServiceOfferingDAO;
import com.marriage.dao.WorkerDAO;
import com.marriage.model.ServiceOffering;
import com.marriage.model.Worker;

import java.util.List;

public class ServiceOfferingService {

    private final ServiceOfferingDAO offeringDAO =
            new ServiceOfferingDAO();

    private final WorkerDAO workerDAO =
            new WorkerDAO();

    // Display all service offerings
    public List<ServiceOffering> getAllOffers() {
        return offeringDAO.findAll();
    }

    // Find offers by selected service and maximum budget
    public List<ServiceOffering> getOffersByServiceAndBudget(
            String selectedService,
            double budget) {

        if (selectedService == null ||
                selectedService.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Service name cannot be empty.");
        }

        if (budget < 0) {
            throw new IllegalArgumentException(
                    "Budget cannot be negative.");
        }

        return offeringDAO.findByServiceAndBudget(
                selectedService.trim(),
                budget
        );
    }

    // Add a service to an existing worker
    public void addOfferingForExistingWorker(
            Integer workerId,
            ServiceOffering offering) {

        Worker worker = workerDAO.findById(workerId);

        if (worker == null) {
            throw new IllegalArgumentException(
                    "Worker ID not found: " + workerId);
        }

        boolean duplicate = offeringDAO.existsOffering(
                workerId,
                offering.getServiceName(),
                offering.getPackageName()
        );

        if (duplicate) {
            throw new IllegalArgumentException(
                    "This service and package already exist for this worker!");
        }

        offering.setWorker(worker);
        offeringDAO.save(offering);
    }
}
