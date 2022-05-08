package com.company;

class Water implements Drink {

    private int amountNeed;

    Water(int amountNeed) {
        this.amountNeed = amountNeed;
    }

    @Override
    public int getAmountNeed() {
        return amountNeed;
    }
}
