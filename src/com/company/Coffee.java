package com.company;

class Coffee implements Drink {

    private int amountNeed;

    Coffee(int amountNeed) {
        this.amountNeed = amountNeed;
    }

    @Override
    public int getAmountNeed() {
        return amountNeed;
    }
}
