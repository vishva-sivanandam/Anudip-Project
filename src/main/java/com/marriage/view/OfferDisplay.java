package com.marriage.view;

import com.marriage.model.ServiceOffering;

import java.util.List;

public class OfferDisplay {

    public void printOffers(List<ServiceOffering> offers) {

        // Check whether any matching offers exist
        if (offers == null || offers.isEmpty()) {
            System.out.println(
                    "No matching service offers found."
            );
            return;
        }

        System.out.println(
                "\n--- Service Offers: Low Price to High Price ---"
        );

        for (ServiceOffering offer : offers) {

            String workerName;

            if (offer.getWorker() == null
                    || offer.getWorker().getName() == null) {

                workerName = "Not assigned";

            } else {
                workerName = offer.getWorker().getName();
            }

            System.out.println(
                    "ID: " + offer.getOfferingId()
                            + " | Category: " + offer.getCategory()
                            + " | Service: " + offer.getServiceName()
                            + " | Package: " + offer.getPackageName()
                            + " | Worker: " + workerName
                            + " | Price: Rs. " + offer.getCustomerPrice()
                            + " " + offer.getPriceUnit()
            );
        }
    }
}
