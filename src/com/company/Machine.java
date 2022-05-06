package com.company;
import java.util.Scanner;

class Machine {

    private final Drink coffee; //declare fields
    private final Scanner scanner;
    private final Container coffeeContainer; //Container
    private int waterHave;

    Machine() {
        coffeeContainer = new CoffeeContainer();
        coffee = new Drink(50, 300); //values for fields
        scanner = new Scanner(System.in);
        waterHave = (int) (Math.random() * 1500);
    }

    void cook() {
        showIngredients();
        while (true) {
            addIngredientsIfNotEnough();
            askCoffee();
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
        coffeeContainer -= coffee.coffeeNeed();
        waterHave -= coffee.waterNeed();
    }

    private boolean isReadyToCook(String input) {
        return input.equalsIgnoreCase("y")
                && coffeeContainer > coffee.coffeeNeed()
                && waterHave > coffee.waterNeed();
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
                """, coffeeContainer, waterHave));
    }

    private boolean isNotEnoughIngredients() {
        return coffee.coffeeNeed() > coffeeContainer || coffee.waterNeed() > waterHave;
    }

    private void askAddIngredients(){
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
        }
    }

    private void addingIngredientsIntoContainer() {
        coffeeContainer += 300;
        waterHave += 800;
    }

    private void printWrongInput(String input) {
        if(!checkInput(input)){
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