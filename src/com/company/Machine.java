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
            addIngredients();
            askCoffee();
            String input = scanner.nextLine();
            if (isReadyToCook(input)) {
                cookCoffee();
                printCoffeePicture();
            } else {
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

    private boolean isEnoughIngredients() {
        return coffee.coffeeNeed() > coffeeHave && coffee.waterNeed() > waterHave;
    }

    private void askAddIngredients(){
        showMessage("""
                Not enough ingredients, add some?
                'y' for 'yes'
                'n' for 'no'
                """);
    }

    private void addIngredients() {
        if (isEnoughIngredients()) {
            askAddIngredients();
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("y")) {
                addingIngredients();
            }
        }
    }

    private void addingIngredients() {
        coffeeHave += 300;
        waterHave += 800;
    }
}

