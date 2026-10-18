
package com.marriage.view;

import com.marriage.model.Customer;
import com.marriage.model.ServiceOffering;
import com.marriage.service.BookingService;
import com.marriage.service.ServiceOfferingService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class BookingInput {

    private final OfferDisplay offerDisplay = new OfferDisplay();

    public void addBooking(
            Scanner scanner,
            ServiceOfferingService offeringService,
            BookingService bookingService) {

        System.out.println("\n--- Customer Booking ---");

        // Check whether services are available.
        List<ServiceOffering> allOffers = offeringService.getAllOffers();

        if (allOffers.isEmpty()) {
            System.out.println(
                    "No service offers available. Please add a service first.");
            return;
        }

        // Step 1: Customer details
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter customer phone: ");
        String phone = scanner.nextLine().trim();

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter marriage date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine().trim());

        // Step 2: Display available categories.
        Set<String> categories =
                new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);

        for (ServiceOffering offer : allOffers) {
            if (offer.getCategory() != null && !offer.getCategory().trim().isEmpty()) {
                categories.add(offer.getCategory().trim());
            }
        }

        System.out.println("\nAvailable categories:");

        for (String category : categories) {
            System.out.println(category);
        }

        // Step 3: Search by category and budget.
        System.out.print("\nEnter service category: ");
        String category = scanner.nextLine().trim();

        System.out.print("Enter your budget: ");
        double budget =
                Double.parseDouble(scanner.nextLine().trim());

        if (budget <= 0) {
            System.out.println(
                    "Budget must be greater than zero. Booking cancelled.");
            return;
        }

        List<ServiceOffering> matchingOffers = offeringService.getOffersByServiceAndBudget(category, budget);

        // Stop if no matching service exists.
        if (matchingOffers.isEmpty()) {
            System.out.println(
                    "Sorry, no service is available in the '"
                            + category + "' category within Rs. " + budget + ".");

            System.out.println("Booking cancelled. Please try another category or budget.");
            return;
        }

        // Step 4: Display matching services.
        System.out.println("\nMatching services:");
        offerDisplay.printOffers(matchingOffers);

        // Step 5: Select a valid service ID.
        System.out.print("Enter service ID: ");
        int selectedId = Integer.parseInt(scanner.nextLine().trim());

        ServiceOffering selectedOffer = null;

        for (ServiceOffering offer : matchingOffers) {
            if (offer.getOfferingId() != null
                    && offer.getOfferingId().intValue() == selectedId) {
                selectedOffer = offer;
                break;
            }
        }

        if (selectedOffer == null) {
            System.out.println(
                    "Invalid service ID. Booking cancelled.");
            return;
        }

        // Step 6: Confirm booking.
        System.out.println(
                "\nSelected service price: Rs. "
                        + selectedOffer.getCustomerPrice());

        System.out.print("Confirm booking? (Y/N): ");
        String confirmation = scanner.nextLine().trim();

        if (!confirmation.equalsIgnoreCase("Y")) {
            System.out.println(
                    "Booking cancelled. No booking was saved.");
            return;
        }

        // Step 7: Save the booking.
        List<ServiceOffering> selectedOffers = new ArrayList<ServiceOffering>();

        selectedOffers.add(selectedOffer);

        Customer customer = new Customer(name, phone, email);

        Integer bookingId = bookingService.createBooking(customer,date, budget, selectedOffers);

        System.out.println("\nBooking is successfully completed!");
        System.out.println("Booking ID: " + bookingId);
    }
}
