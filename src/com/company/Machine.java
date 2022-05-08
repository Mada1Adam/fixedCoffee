package com.company;
import java.util.Scanner;

class Machine {

    private final Drink coffee;
    private final Drink water;
    private final Drink milk;
    private final Container milkContainer;
    private final Container coffeeContainer;
    private final Container waterContainer;
    private final Scanner scanner;

    Machine() {
        coffeeContainer = new CoffeeContainer(); //values for fields
        waterContainer = new WaterContainer();
        milkContainer = new MilkContainer();
        coffee = new Coffee(30);
        water = new Water(300);
        milk = new Milk(200);
        coffeeContainer.addRandomAmount();
        waterContainer.addRandomAmount();
        milkContainer.addRandomAmount();
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
        coffeeContainer.use(coffee.getAmountNeed());
        waterContainer.use(water.getAmountNeed());
    }

    private boolean isReadyToCook(String input) {
        return input.equalsIgnoreCase("y")
                && coffeeContainer.amountWeHave() > coffee.getAmountNeed()
                && waterContainer.amountWeHave() > water.getAmountNeed();
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
        return coffee.getAmountNeed() > coffeeContainer.amountWeHave() || water.getAmountNeed() > waterContainer.amountWeHave();
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