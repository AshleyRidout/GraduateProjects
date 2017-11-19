package com.example.ashleyridout.yoursforaday;

public class KeeperItem {
    //variables
    private int id;
    private String name;
    private double price;
    private int quantity;

    //empty default constructor
    public KeeperItem() {
    }

    //constructor with 4 arguments
    public KeeperItem(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * @Intent get and set methods for each parameter of com.example.ashleyridout.yoursforaday.KeeperItem constructor
     */

    //get id method
    public int getID() {
       return id;
    }

    //set id method
   public void setID(Integer id) {
       this.id = id;
    }

    //get name method
    public String getName() {
        return name;
    }

    //set name method
    public void setName(String name) {
        this.name = name;
    }

    //get price method
    public double getPrice() {
        return price;
    }

    //set price method
    public void setPrice(Double price) {
        this.price = price;
    }

    //get quantity method
    public int getQuantity() {
        return quantity;
    }

    //set quantity method
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
