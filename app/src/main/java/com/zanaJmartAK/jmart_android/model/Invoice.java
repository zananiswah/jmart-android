package com.zanaJmartAK.jmart_android.model;

import java.util.Date;

/**
 * model as an Invoice similar to backend
 * @author Zana Niswah Awahita
 */

public class Invoice extends Serializable {
    public static enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED;
    }

    public static enum Rating{
        NONE,BAD,NEUTRAL,GOOD;
    }

    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
}