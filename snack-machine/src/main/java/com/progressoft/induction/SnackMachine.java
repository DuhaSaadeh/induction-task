package com.progressoft.induction;

public class SnackMachine {

    public static final int DEFAULT_QUANTITY;
    static {
        DEFAULT_QUANTITY  = 10;
    }

    private  Money moneyInsideMachine;
    private  Money moneyInserted;

    private  Snack chipsType;
    private  Snack chewingGumsType;
    private  Snack chocolatesType;

    public SnackMachine() {
        this.moneyInsideMachine = new Money(Money.ZERO.getMoney());
        this.moneyInserted      = new Money(Money.ZERO.getMoney());
        this.chewingGumsType    = new Snack(DEFAULT_QUANTITY, SnackType.CHEWING_GUM_PRICE);
        this.chipsType          = new Snack(DEFAULT_QUANTITY, SnackType.CHIPS_PRICE);
        this.chocolatesType     = new Snack(DEFAULT_QUANTITY, SnackType.CHOCOLATE_PRICE);
    }

    public Money moneyInside() {
        return this.moneyInsideMachine;
    }

    public void insertMoney(Money amount) {
        if(Money.acceptedMoney(amount) && amount != null) {
            this.moneyInserted = new Money(this.moneyInserted.add(amount).getMoney());
        }
        else throw new IllegalArgumentException("snack machine does not accept non predefined money units or null");
    }

    public Money moneyInTransaction() {
        return this.moneyInserted;
    }

    public Money buySnack(SnackType snack) {
        Money price;
        int quantity;
        switch (snack){
            case CHEWING_GUM:
                price    = chewingGums().price();
                quantity = chewingGums().quantity();
                break;
            case CHIPS:
                price    = chips().price();
                quantity = chips().quantity();
                break;
            case CHOCOLATE:
                price    = chocolates().price();
                quantity = chocolates().quantity();
                break;
            default:
                price    = Money.ZERO;
                quantity = 0;
                break;
        }

        if(moneyInTransaction().equals(Money.ZERO)){
            throw new IllegalStateException("buy without inserting money");
        }
        if(quantity < 1){
            throw new IllegalStateException("buying unavailable quantity of a snack");
        }
        if(moneyInTransaction().isLessThan(price)){
            throw new IllegalStateException("buying a snack after inserting money less than snack price");
        }
        if(price.isLessThan(moneyInTransaction()) && quantity != 0){
            decreaseQuantity(snack);
            Money change = new Money(moneyInTransaction().subtract(price).getMoney());
            this.moneyInserted = new Money(Money.ZERO.getMoney());
            return change;
        }
        if(moneyInTransaction().getMoney().floatValue() == (price.getMoney().floatValue()) && quantity != 0){
            decreaseQuantity(snack);
            this.moneyInsideMachine = new Money(this.moneyInsideMachine.add(price).getMoney());
            this.moneyInserted = new Money(Money.ZERO.getMoney());
            return Money.ZERO;
        }
        return null;
    }

    public void decreaseQuantity(SnackType snack){
        switch (snack){
            case CHEWING_GUM:
                chewingGumsType.decreaseQuantity();
                break;
            case CHIPS:
                chipsType.decreaseQuantity();
                break;
            case CHOCOLATE:
                chocolatesType.decreaseQuantity();
                break;
        }
    }

    public Snack chips() {
        return chipsType;
    }

    public Snack chewingGums() {
        return chewingGumsType;
    }

    public Snack chocolates() {
        return chocolatesType;
    }

}
