package com.company;

class CoffeeContainer implements Container {

    private int coffeeHave;

    @Override
    public void use(int amountToUse) {
        coffeeHave -= amountToUse;
    }

    @Override
    public void add(int amountToAdd) {
        coffeeHave += amountToAdd;
    }

    @Override
    public int amountWeHave() {
        return coffeeHave;
    }

    @Override
    public void addRandomAmount() {
        coffeeHave += Math.random() * 800;
    }

}
