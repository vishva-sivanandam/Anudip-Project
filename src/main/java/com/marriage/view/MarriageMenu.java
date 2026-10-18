
package com.marriage.view;

import com.marriage.service.BookingService;
import com.marriage.service.ServiceOfferingService;
import com.marriage.service.WorkerService;

import java.util.Scanner;

public class MarriageMenu {

    private final WorkerService workerService = new WorkerService();
    private final ServiceOfferingService offeringService = new ServiceOfferingService();
    private final BookingService bookingService = new BookingService();

    private final WorkerInput workerInput = new WorkerInput();
    private final BookingInput bookingInput = new BookingInput();
    private final OfferDisplay offerDisplay = new OfferDisplay();

    public void start(Scanner scanner) {

        boolean running = true;

        while (running) {

            System.out.println("\n===== Marriage Working Management System =====");
            System.out.println("1. Add worker and service offer");
            System.out.println("2. View all service offers");
            System.out.println("3. Create marriage booking");
            System.out.println("4. Add service to existing worker");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            try {
                if (choice.equals("1")) {

                    workerInput.addWorker(scanner, workerService);

                } else if (choice.equals("2")) {

                    offerDisplay.printOffers(
                            offeringService.getAllOffers());

                } else if (choice.equals("3")) {

                    // Booking and budget search happen together.
                    bookingInput.addBooking(
                            scanner,
                            offeringService,
                            bookingService);

                } else if (choice.equals("4")) {

                    workerInput.addServiceToExistingWorker(
                            scanner, offeringService);

                } else if (choice.equals("0")) {

                    running = false;
                    System.out.println(
                            "Exiting Marriage Working Management System.");

                } else {

                    System.out.println(
                            "Invalid choice. Select 0 to 4.");
                }

            } catch (Exception exception) {
                System.out.println(
                        "Operation failed: " + exception.getMessage());
            }
        }
    }
}
