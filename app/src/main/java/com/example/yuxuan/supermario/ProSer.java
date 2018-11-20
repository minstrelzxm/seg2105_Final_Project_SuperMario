package com.example.yuxuan.supermario;

public class ProSer {
    private String ProID, Date,StartTime,EndTime;
    private Service SerID;
    public ProSer(String ProID,Service SerID,String Date,String StartTime,String EndTime){
        this.ProID = ProID;
        this.SerID = SerID;
        this.Date = Date;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
    }
    public String toString(){
        return "ProviderNameIs"+ProID+". \nOffer Server: "+SerID+"Date: "+getDate()+", from "+StartTime+" to "+getEndTime();
    }

    public void setProID(String ProId1){
        ProID = ProId1;
    }
    public String getProID(){
        return ProID;
    }
    public void setSerID(Service SerID1){
        SerID = SerID1;
    }
    public Service getSerID(){
        return SerID;
    }
    public void setDate(String Date1){
        ProID = Date1;
    }
    public String getDate(){
        return Date;
    }
    public void setStartTime(String StartTime1){
        StartTime = StartTime1;
    }
    public String getStartTime(){
        return StartTime;
    }
    public void setEndTime(String EndTime1){
        EndTime = EndTime1;
    }
    public String getEndTime(){
        return EndTime;
    }

}
