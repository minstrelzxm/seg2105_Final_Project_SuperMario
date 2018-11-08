package com.example.yuxuan.supermario;

/*
An administrator can create several service
We are going to use Lab5 productCatalog code as our source code
 */
public class Service {
    private String serviceId;
    private String typeOfService;
    private double hourRate;

    public Service(String sID,String serviceType, double hr){
        this.serviceId = sID;
        this.typeOfService = serviceType;
        this.hourRate = hr;
    }

    public Service(String serviceType, int hr){
        this.typeOfService = serviceType;
        this.hourRate = hr;
    }

    public void setTypeOfService(String newTypeOfService){
        this.typeOfService = newTypeOfService;
    }

    public void setHourRate(int newHourRate){
        this.hourRate = newHourRate;
    }

    public void setId(String newId){
        this.serviceId = newId;
    }

    public String getServiceId(){
        return this.serviceId;
    }

    public String getTypeOfService(){
        return this.typeOfService;
    }

    public double getHourRate(){
        return this.hourRate;
    }

    public void deleteService(){
        this.hourRate = 0;
        this.typeOfService = "";
    }

}
