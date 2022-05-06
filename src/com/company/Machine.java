package com.company;
import java.util.Scanner;

class Machine {

    private final Drink coffee; //declare fields
    private final Scanner scanner;
    private final Container coffeeContainer;
    private final Container waterContainer;

    Machine() {
        coffeeContainer = new CoffeeContainer(); //values for fields
        coffeeContainer.addRandomAmount();
        waterContainer = new WaterContainer();
        waterContainer.addRandomAmount();
        coffee = new Drink(50, 300);
        scanner = new Scanner(System.in);
    }

    void cook() {
        showIngredients();
        while (true) {
            addIngredientsIfNotEnough();
            String input = scanner.nextLine();
            printWrongInput(input);
            if (isReadyToCook(input)) {
                cookCoffee();
                printCoffeePicture();
            } else if (noCoffee(input)) {
                showMessage("Goodbye");
                break;
            }
        }
    }

    private void showMessage(String message) {
        System.out.println(message);
    }

    private void askCoffee() {
        showMessage("""
                Some coffee?
                Type:
                'y' for 'yes'
                'n' for 'no'
                """);
    }

    private void cookCoffee() {
        coffeeContainer.use(coffee.coffeeNeed());
        waterContainer.use(coffee.waterNeed());
    }

    private boolean isReadyToCook(String input) {
        return input.equalsIgnoreCase("y")
                && coffeeContainer.amountWeHave() > coffee.coffeeNeed()
                && waterContainer.amountWeHave() > coffee.waterNeed();
    }

    private void printCoffeePicture() {
        showMessage("""
                Here is your coffee:
                ┈┈┈┈┈┈♡┈┈┈┈
                ┈┈┈┈┈╭╯♡┈┈┈
                ┈┈╱▔╭╯▔╲┈┈┈
                ┈▕╲▂▂▂▂╱▏┈┈
                ┈┈▏┊┊┊┊▕━╮┈
                ┈┈▏┊┊┊┊▕┈┃┈
                ┈┈▏┊┊┊┊▕━╯┈
                ┈┈╲▂▂▂▂╱┈┈┈
                """);
    }

    private void showIngredients() {
        showMessage(String.format("""
                coffee you have: %d
                water you have: %d
                """, coffeeContainer.amountWeHave(), waterContainer.amountWeHave()));
    }

    private boolean isNotEnoughIngredients() {
        return coffee.coffeeNeed() > coffeeContainer.amountWeHave() || coffee.waterNeed() > waterContainer.amountWeHave();
    }

    private void askAddIngredients() {
        showMessage("""
                Not enough ingredients, add some?
                'y' for 'yes'
                'n' for 'no'
                """);
    }

    private void addIngredientsIfNotEnough() {
        if (isNotEnoughIngredients()) {
            askAddIngredients();
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("y")) {
                addingIngredientsIntoContainer();
            }
        } else {
            askCoffee();
        }
    }

    private void addingIngredientsIntoContainer() {
        coffeeContainer.add(300);
        waterContainer.add(800);
    }

    private void printWrongInput(String input) {
        if (!checkInput(input)) {
            showMessage("Wrong input, try again");
        }
    }

    private boolean checkInput(String input) {
        return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n");
    }

    public boolean noCoffee(String input) {
        return input.equalsIgnoreCase("n");
    }
}