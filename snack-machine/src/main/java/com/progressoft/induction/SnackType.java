package com.progressoft.induction;

import java.math.BigDecimal;

public enum SnackType {
    CHEWING_GUM , CHIPS , CHOCOLATE;
    public static final Money CHEWING_GUM_PRICE;
    public static final Money CHIPS_PRICE;
    public static final Money CHOCOLATE_PRICE;
    static {
        CHEWING_GUM_PRICE = new Money(BigDecimal.valueOf(0.50));
        CHIPS_PRICE       = new Money(BigDecimal.valueOf(1.00));
        CHOCOLATE_PRICE   = new Money(BigDecimal.valueOf(2.00));
    }

    SnackType(){

    }
}
