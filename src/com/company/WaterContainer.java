package com.company;

class WaterContainer implements Container {

    private int waterHave;

    @Override
    public void use(int amountToUse) {
        waterHave -= amountToUse;
    }

    @Override
    public void add(int amountToAdd) {
        waterHave += amountToAdd;
    }

    @Override
    public int amountWeHave() {
        return waterHave;
    }

    @Override
    public void addRandomAmount() {
        waterHave += Math.random() * 1500;
    }
}
