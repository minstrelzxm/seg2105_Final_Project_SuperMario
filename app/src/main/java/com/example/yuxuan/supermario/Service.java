package com.example.yuxuan.supermario;
/*
Services that admin can create
 */
public class Service {
    private String typeOfService;
    private int hourRate;

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

    public String getTypeOfService(){
        return this.typeOfService;
    }

    public int getHourRate(){
        return this.hourRate;
    }

    public void deleteService(){
        this.hourRate = 0;
        this.typeOfService = "";
    }

}
