package com.zanaJmartAK.jmart_android.model;

/**
 * model as a Product similar to backend
 * @author Zana Niswah Awahita
 */

public class Product extends Serializable{
    public int accountId;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;
    public ProductCategory category;

    public String toString() {
        return this.name;
    }
}
