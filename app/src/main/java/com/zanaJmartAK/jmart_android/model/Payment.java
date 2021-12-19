package com.zanaJmartAK.jmart_android.model;


import java.util.ArrayList;
import java.util.Date;

/**
 * model as a Payment similar to backend
 * @author Zana Niswah Awahita
 */


public class Payment extends Invoice{
    public Shipment shipment;
    public int productCount;
    public ArrayList<Record> history = new ArrayList<Record>();

    public static class Record{
        public final Date date;
        public String message;
        public Status status;

        public Record(Status status, String message){
            this.date = java.util.Calendar.getInstance().getTime();
            this.status = Status.WAITING_CONFIRMATION;
            this.message = message;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.productId);
    }
}