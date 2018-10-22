package com.example.yuxuan.supermario;

public class Account {
    String username;
    String password;
    public Account(String username, String password){
        this.username=username;
        this.password=password;
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

