
package com.marriage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "service_offerings")
public class ServiceOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer offeringId;

    private String category;
    private String serviceName;
    private String packageName;
    private double customerPrice;
    private String priceUnit;

    @ManyToOne(optional = false)
    private Worker worker;

    public ServiceOffering() {
    }

    public ServiceOffering(
            String category,
            String serviceName,
            String packageName,
            double customerPrice,
            String priceUnit,
            Worker worker) {

        this.category = category;
        this.serviceName = serviceName;
        this.packageName = packageName;
        this.customerPrice = customerPrice;
        this.priceUnit = priceUnit;
        this.worker = worker;
    }

    public Integer getOfferingId() {
        return offeringId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public double getCustomerPrice() {
        return customerPrice;
    }

    public void setCustomerPrice(double customerPrice) {
        this.customerPrice = customerPrice;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
