package com.progressoft.induction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public final class Money {

    public static final Money ZERO;
    public static final Money QUARTER_DINAR;
    public static final Money HALF_DINAR;
    public static final Money DINAR;
    public static final Money FIVE_DINAR;
    public static final Money TEN_DINAR;
    public static final ArrayList<Money> acceptedMoneyUnits;

    static {
        ZERO            = new Money(BigDecimal.valueOf(0));
        QUARTER_DINAR   = new Money(BigDecimal.valueOf(0.25));
        HALF_DINAR      = new Money(BigDecimal.valueOf(0.50));
        DINAR           = new Money(BigDecimal.valueOf(1.00));
        FIVE_DINAR      = new Money(BigDecimal.valueOf(5.00));
        TEN_DINAR       = new Money(BigDecimal.valueOf(10.00));
        acceptedMoneyUnits = new ArrayList<>(
                Arrays.asList(
                        QUARTER_DINAR,
                        HALF_DINAR,
                        DINAR,
                        FIVE_DINAR,
                        TEN_DINAR
                )
        );
    }

    private BigDecimal money;

    public Money(BigDecimal amount){
        if(amount.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgumentException("money with negative value");
        }

        this.money = amount;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public Money add(Money amount){
        this.money = this.money.add(amount.getMoney());
        return new Money(this.money);
    }

    public Money subtract(Money amount){
        if(this.money.compareTo(amount.money) >= 0)
            this.money = this.money.subtract(amount.getMoney());
        else throw new IllegalArgumentException("subtract money from smaller money");
        return new Money(this.money);
    }

    public boolean isLessThan(Money amount){
        return null != amount && this.money.compareTo(amount.getMoney()) < 0;
    }

    public static boolean acceptedMoney(Money amount){
        return acceptedMoneyUnits.contains(amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this.money.floatValue() == ((Money) obj).getMoney().floatValue()) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        return false;
    }

}
