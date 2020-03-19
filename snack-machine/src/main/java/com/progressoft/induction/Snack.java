package com.progressoft.induction;

public class Snack {

    private Money itemPrice;
    private int itemQuantity;

    public Snack(int quantity, Money price){
        this.itemPrice = price;
        this.itemQuantity = quantity;
    }

    public Money price(){
        return this.itemPrice;
    }

    public int quantity() {
        return this.itemQuantity;
    }

    public void decreaseQuantity(){
        this.itemQuantity -= 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        Snack compareSnack = (Snack) obj;
        return this.equals(compareSnack);
    }
}
