package com.example.yuxuan.supermario;

public class ServiceProviderInfo {
    private String _id;
    private String _address;
    private String _phoneNum;
    private String _nameOfCompany;
    private String _generalInfo;
    private String _licensed;

    public ServiceProviderInfo(){
    }
    public ServiceProviderInfo(String id, String address, String phoneNum, String nameOfCompany, String generalInfo, String licensed){
        _id=id;
        _address=address;
        _phoneNum=phoneNum;
        _nameOfCompany=nameOfCompany;
        _generalInfo=generalInfo;
        _licensed=licensed;
    }
    public ServiceProviderInfo(String address, String phoneNum, String nameOfCompany, String generalInfo, String licensed){

        _address=address;
        _phoneNum=phoneNum;
        _nameOfCompany=nameOfCompany;
        _generalInfo=generalInfo;
        _licensed=licensed;
    }
    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }

    public void setAddress(String address) {
        _address=address;
    }
    public String getAddress() {
        return _address;
    }

    public void setPhoneNum(String phoneNum) {
        _phoneNum=phoneNum;
    }
    public String getPhoneNum() {return _phoneNum;}

    public void setNameOfCompany(String nameOfCompany) {
        _nameOfCompany=nameOfCompany;
    }
    public String getNameOfCompany() {return _nameOfCompany;}

    public void setGeneralInfo(String generalInfo) {_generalInfo=generalInfo;}
    public String getGeneralInfo() {return _generalInfo;}

    public void setLicensed(String licensed) {_licensed=licensed;;}
    public String getLicensed() {return _licensed;}
}
