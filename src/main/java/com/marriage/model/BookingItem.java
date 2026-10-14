
package com.marriage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking_items")
public class BookingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingItemId;

    @ManyToOne(optional = false)
    private Booking booking;

    @ManyToOne(optional = false)
    private ServiceOffering offering;

    private double agreedPrice;

    public BookingItem() {
    }

    public BookingItem(
            Booking booking,
            ServiceOffering offering,
            double agreedPrice) {

        this.booking = booking;
        this.offering = offering;
        this.agreedPrice = agreedPrice;
    }

    public Integer getBookingItemId() {
        return bookingItemId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public ServiceOffering getOffering() {
        return offering;
    }

    public void setOffering(ServiceOffering offering) {
        this.offering = offering;
    }

    public double getAgreedPrice() {
        return agreedPrice;
    }

    public void setAgreedPrice(double agreedPrice) {
        this.agreedPrice = agreedPrice;
    }
}
