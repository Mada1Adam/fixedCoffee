package com.company;

class MilkContainer implements Container {

    private int milkHave;

    @Override
    public void use(int amountToUse) {
            milkHave -= amountToUse;
    }

    @Override
    public void add(int amountToAdd) {
        milkHave += amountToAdd;
    }

    @Override
    public int amountWeHave() {
        return milkHave;
    }

    @Override
    public void addRandomAmount() {
        milkHave += Math.random() * 1000;
    }
}
