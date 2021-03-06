package com.mastermindapps.justcoffee;

class ToppingsDataType {

    private String nameOfTopping;
    private int idOfTopping;
    private int priceOfTopping;

    protected ToppingsDataType(String nameOfTopping, int idOfTopping, int priceOfTopping) {
        this.nameOfTopping = nameOfTopping;
        this.idOfTopping = idOfTopping;
        this.priceOfTopping = priceOfTopping;
    }

    protected String getNameOfTopping() {
        return nameOfTopping;
    }

    protected int getIdOfTopping() {
        return idOfTopping;
    }

    protected int getPriceOfTopping() {
        return priceOfTopping;
    }
}
