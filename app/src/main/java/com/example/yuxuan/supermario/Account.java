package com.example.yuxuan.supermario;

public class Account {
    String username;
    String password;
    int type;

    public Account(){}

    public Account(String username, String password, int type){
        this.username=username;
        this.password=password;
        this.type=type;
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

