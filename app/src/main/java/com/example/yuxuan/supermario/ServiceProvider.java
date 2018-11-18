package com.example.yuxuan.supermario;

public class ServiceProvider {

    private String address;
    private String phoneNumber;
    private String companyName;
    private String generalDescription;
    private String licensed;



    public ServiceProvider(String address,String phoneNumber,String companyName){
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.generalDescription = "";
        this.licensed = "NO";
    }

    public ServiceProvider(String address,String phoneNumber,String companyName,String generalDescription){
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.generalDescription = generalDescription;
        this.licensed = "NO";
    }

    public ServiceProvider(String address,String phoneNumber,String companyName,String generalDescription,String licensed){
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.generalDescription = generalDescription;
        this.licensed = licensed;
    }

    public void setGeneralDescription(String generalDescription){
        this.generalDescription = generalDescription;
    }

    public void setLicensed(String licensed){
        this.licensed = licensed;
    }
}
