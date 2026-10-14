
package com.marriage.view;

import com.marriage.model.Customer;
import com.marriage.model.ServiceOffering;
import com.marriage.service.BookingService;
import com.marriage.service.ServiceOfferingService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingInput {

    private final OfferDisplay offerDisplay = new OfferDisplay();

    public void addBooking(
            Scanner scanner,
            ServiceOfferingService offeringService,
            BookingService bookingService) {

        System.out.println("\n--- Create Marriage Booking ---");

        System.out.print("Customer name: ");
        String name = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Marriage date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Total budget: ");
        double budget = Double.parseDouble(scanner.nextLine());

        List<ServiceOffering> offers = offeringService.getAllOffers();

        offerDisplay.printOffers(offers);

        if (offers.isEmpty()) {
            System.out.println("No service offers available. Add an offer first.");
            return;
        }

        System.out.print(
                "Enter offering IDs separated by commas (example: 1,3,4): "
        );

        String[] ids = scanner.nextLine().split(",");

        List<ServiceOffering> selected =
                new ArrayList<ServiceOffering>();

        for (int i = 0; i < ids.length; i++) {

            int id = Integer.parseInt(ids[i].trim());

            ServiceOffering match = null;

            for (ServiceOffering offer : offers) {

                if (offer.getOfferingId() != null
                        && offer.getOfferingId().intValue() == id) {

                    match = offer;
                    break;
                }
            }

            if (match == null) {
                throw new IllegalArgumentException(
                        "Offer ID not found: " + id
                );
            }

            boolean alreadySelected = false;

            for (ServiceOffering existing : selected) {

                if (existing.getOfferingId().equals(
                        match.getOfferingId())) {

                    alreadySelected = true;
                    break;
                }
            }

            if (!alreadySelected) {
                selected.add(match);
            }
        }

        Integer bookingId = bookingService.createBooking(
                new Customer(name, phone, email),
                date,
                budget,
                selected
        );

        System.out.println(
                "Booking saved successfully. Booking ID: " + bookingId
        );
    }
}
