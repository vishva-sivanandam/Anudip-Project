package com.marriage.view;
import com.marriage.dao.WorkerDAO;
import com.marriage.model.ServiceOffering;
import com.marriage.model.Worker;
import com.marriage.service.WorkerService;
import com.marriage.service.ServiceOfferingService;

import java.util.List;
import java.util.Scanner;

public class WorkerInput {

    private final WorkerDAO workerDAO = new WorkerDAO();

    // 1. Add a new worker and their first service
    public void addWorker(Scanner scanner, WorkerService workerService) {

        System.out.println("\n--- Add Worker and Service Offer ---");

        System.out.print("Worker name: ");
        String name = scanner.nextLine();

        System.out.print("Work type (Catering/Photography/Hall/etc.): ");
        String workType = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Service category: ");
        String category = scanner.nextLine();

        System.out.print("Service name: ");
        String serviceName = scanner.nextLine();

        System.out.print("Package name: ");
        String packageName = scanner.nextLine();

        System.out.print("Customer price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Price unit (event/day/person): ");
        String unit = scanner.nextLine();

        Worker worker = new Worker(
                name, workType, phone, email);

        ServiceOffering offering = new ServiceOffering(
                category, serviceName, packageName,
                price, unit, worker);

        workerService.addWorkerAndOffering(worker, offering);

        System.out.println("Worker and service offer saved.");
    }

    // 2. Add a service to an existing worker
    public void addServiceToExistingWorker(
            Scanner scanner,
            ServiceOfferingService offeringService) {

        System.out.println("\n--- Add Service to Existing Worker ---");

        List<Worker> workers = workerDAO.findAll();

        if (workers.isEmpty()) {
            System.out.println("No workers found. Add a worker first.");
            return;
        }

        System.out.println("\n--- Available Workers ---");

        for (Worker worker : workers) {
            System.out.println(
                    "Worker ID: " + worker.getWorkerId()
                            + " | Name: " + worker.getName()
                            + " | Work Type: " + worker.getWorkType());
        }

        System.out.print("Enter existing Worker ID: ");
        Integer workerId = Integer.parseInt(scanner.nextLine());

        System.out.print("Service category: ");
        String category = scanner.nextLine();

        System.out.print("Service name: ");
        String serviceName = scanner.nextLine();

        System.out.print("Package name: ");
        String packageName = scanner.nextLine();

        System.out.print("Customer price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Price unit (event/day/person): ");
        String unit = scanner.nextLine();

        ServiceOffering offering = new ServiceOffering();

        offering.setCategory(category);
        offering.setServiceName(serviceName);
        offering.setPackageName(packageName);
        offering.setCustomerPrice(price);
        offering.setPriceUnit(unit);

        offeringService.addOfferingForExistingWorker(
                workerId, offering);

        System.out.println(
                "Service offer saved for Worker ID: " + workerId);
    }
}
