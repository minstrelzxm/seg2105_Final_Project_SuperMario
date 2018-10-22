package com.example.yuxuan.supermario;

import com.google.android.gms.common.internal.AccountType;

public class Account {
    String username;
    String password;
    //AccountType types;
    //int type;

    private MyAccountType AccountTypes;




    public Account(String username, String password, MyAccountType types){
        this.username=username;
        this.password=password;
        AccountTypes = types;
        //this.type=type;
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
    public MyAccountType getAccountTypes() {
        return AccountTypes;
    }

    public void setAccountTypes(MyAccountType accountTypes) {
        AccountTypes = accountTypes;
    }

}

