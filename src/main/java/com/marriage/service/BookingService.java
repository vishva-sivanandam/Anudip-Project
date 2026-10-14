
package com.marriage.service;

import com.marriage.dao.BookingDAO;
import com.marriage.model.Booking;
import com.marriage.model.BookingItem;
import com.marriage.model.Customer;
import com.marriage.model.ServiceOffering;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private final BookingDAO bookingDAO = new BookingDAO();

    public Integer createBooking(
            Customer customer,
            LocalDate date,
            double budget,
            List<ServiceOffering> selectedOffers) {

        if (customer.getName() == null
                || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Customer name is required."
            );
        }

        if (date == null) {
            throw new IllegalArgumentException(
                    "Marriage date is required."
            );
        }

        if (budget <= 0) {
            throw new IllegalArgumentException(
                    "Budget must be greater than zero."
            );
        }

        if (selectedOffers == null || selectedOffers.isEmpty()) {
            throw new IllegalArgumentException(
                    "Choose at least one service."
            );
        }

        double total = 0;

        List<BookingItem> items = new ArrayList<BookingItem>();

        for (ServiceOffering offer : selectedOffers) {

            total += offer.getCustomerPrice();

            BookingItem item = new BookingItem(
                    null,
                    offer,
                    offer.getCustomerPrice()
            );

            items.add(item);
        }

        if (total > budget) {
            throw new IllegalArgumentException(
                    "Selected services cost Rs. " + total
                            + ", which exceeds your budget."
            );
        }

        Booking booking = new Booking(customer, date, budget, "CONFIRMED");

        Integer bookingId = bookingDAO.saveBooking(
                customer,
                booking,
                items
        );

        System.out.println("Selected services total: Rs. " + total);

        return bookingId;
    }
}
