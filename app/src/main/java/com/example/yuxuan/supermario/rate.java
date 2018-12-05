package com.example.yuxuan.supermario;

public class rate {
    private String id;
    private double rate;
    private int man;
    public rate(double rate, int man){
        this.rate = rate;
        this.man = man;
    }
    public double result(){
        return rate/man;
    }

    public double getRate(){
        return rate;
    }

    public int getMan(){
        return man;
    }

    public void setMan(int man) {
        this.man = man;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "this rate is:"+result();
    }
}
