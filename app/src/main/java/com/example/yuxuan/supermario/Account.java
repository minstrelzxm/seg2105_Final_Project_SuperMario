package com.example.yuxuan.supermario;

public class Account {
    String username;
    String password;
    //AccountType types;
    //int type;


    public enum AccountType{
        administrator,serviceProviders,homeOwners
    }
    private AccountType AccountTypes;

    public Account(String username, String password, AccountType types){
        this.username=username;
        this.password=password;
        AccountTypes = types;
        //this.type=type;
    }

    public AccountType gettypes(){
        return AccountTypes;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public void setUsername(String newName){
        this.username=newName;
    }
    public void setPassword(String newPass){
        this.password=newPass;
    }
}

