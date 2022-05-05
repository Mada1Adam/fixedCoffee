package com.company;
import java.util.Scanner;

class Machine {

    private final Drink coffee; //declare fields
    private final Scanner scanner;
    private int coffeeHave;
    private int waterHave;

    Machine() {
        coffee = new Drink(50, 300); //values for fields
        scanner = new Scanner(System.in);
        coffeeHave = (int) (Math.random() * 800);
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
        coffeeHave -= coffee.coffeeNeed();
        waterHave -= coffee.waterNeed();
    }

    private boolean isReadyToCook(String input) {
        return input.equalsIgnoreCase("y")
                && coffeeHave > coffee.coffeeNeed()
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
                """, coffeeHave, waterHave));
    }

    private boolean isNotEnoughIngredients() {
        return coffee.coffeeNeed() > coffeeHave || coffee.waterNeed() > waterHave;
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
        coffeeHave += 300;
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