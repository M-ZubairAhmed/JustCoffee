package com.mastermindapps.justcoffee;

public class Toppings {

    private String nameOfTopping;
    private int idOfTopping;
    private float priceOfTopping;

    public Toppings(String nameOfTopping, int idOfTopping, float priceOfTopping) {
        this.nameOfTopping = nameOfTopping;
        this.idOfTopping = idOfTopping;
        this.priceOfTopping = priceOfTopping;
    }

    public String getNameOfTopping() {
        return nameOfTopping;
    }

    public int getIdOfTopping() {
        return idOfTopping;
    }

    public float getPriceOfTopping() {
        return priceOfTopping;
    }
}
