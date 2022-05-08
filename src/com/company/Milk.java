package com.company;

class Milk implements Drink{

    private int amountNeed;

    Milk(int amountNeed) {
        this.amountNeed = amountNeed;
    }

    @Override
    public int getAmountNeed() {
        return amountNeed;
    }
}
