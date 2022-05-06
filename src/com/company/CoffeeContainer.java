package com.company;

class CoffeeContainer implements Container {

    private int coffeeHave;

    @Override
    public void use() {

    }


    @Override
    public void add(int amountToAdd) {
        coffeeHave = (int) (Math.random() * 1500);
    }
}
