package com.example.yuxuan.supermario;

public class Account {
    String username;
    String password;
    //AccountType types;
    //int type;


    public enum AccountType{
        administrator,service_providers,homeowners
    }

    public Account(String username, String password, AccountType types){
        this.username=username;
        this.password=password;
        AccountType AccountTypes = types;
        //this.type=type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public void setUsername(String newname){
        this.username=newname;
    }
    public void setPassword(String newpass){
        this.password=newpass;
    }
}

