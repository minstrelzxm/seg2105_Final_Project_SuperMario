package com.example.yuxuan.supermario;

public class ServiceProvider {

    private String address;
    private String phoneNumber;
    private String companyName;
    private String generalDescription;
    private String licensed;
    private float rating;



    public ServiceProvider(String address,String phoneNumber,String companyName){
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.generalDescription = "";
        this.licensed = "NO";
        this.rating = (float) 0.00;
    }

    public ServiceProvider(String address,String phoneNumber,String companyName,String generalDescription){
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.generalDescription = generalDescription;
        this.licensed = "NO";
        this.rating = (float) 0.00;
    }

    public ServiceProvider(String address,String phoneNumber,String companyName,String generalDescription,String licensed){
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.generalDescription = generalDescription;
        this.licensed = licensed;
        this.rating = (float) 0.00;
    }

    public ServiceProvider(String address, String phoneNumber, String companyName, String generalDescription, String licensed, float rating){
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.generalDescription = generalDescription;
        this.licensed = licensed;
        this.rating = rating;
    }

    public void setGeneralDescription(String generalDescription){
        this.generalDescription = generalDescription;
    }

    public void setLicensed(String licensed){

        this.licensed = licensed;
    }
}
