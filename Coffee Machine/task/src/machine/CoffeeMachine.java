package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    private boolean machineOn;

    private CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
        this.machineOn = true;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);

        while (machine.isOn()) {
            System.out.print("Write action (buy, fill, take, remaining, exit): ");
            String action = scanner.next();
            System.out.println();

            switch (action) {
                case "buy":
                    System.out.println("What do you want to buy?");
                    System.out.println("1 - espresso,");
                    System.out.println("2 - latte,");
                    System.out.println("3 - cappuccino,");
                    System.out.print("back - to main menu: ");
                    String choice = scanner.next();

                    if (!choice.equals("back")) {
                        machine.buyCoffee(Integer.parseInt(choice));
                    }

                    break;

                case "fill":
                    System.out.print("Write how many ml of water do you want to add: ");
                    int addWater = scanner.nextInt();

                    System.out.print("Write how many ml of milk do you want to add: ");
                    int addMilk = scanner.nextInt();

                    System.out.println("Write how many grams of coffee beans do you want to add: ");
                    int addBeans = scanner.nextInt();

                    System.out.println("Write how many disposable cups of coffee do you want to add: ");
                    int addCups = scanner.nextInt();

                    machine.fillMachine(addWater, addMilk, addBeans, addCups);
                    System.out.println();
                    break;

                case "take":
                    machine.takeMoney();
                    break;

                case "remaining":
                    machine.stateMachine();
                    break;

                case "exit":
                    machine.turnOff();
                    break;

                default:
                    break;
            }
        }
    }

    private void stateMachine() {
        System.out.println("The coffee machine has:");
        System.out.println(this.water + " of water");
        System.out.println(this.milk + " of milk");
        System.out.println(this.beans + " of coffee beans");
        System.out.println(this.cups + " of disposable cups");
        System.out.println("$" + this.money + " of money");
        System.out.println();
    }

    private boolean isEnoughResources(int minWater, int minMilk, int minBeans) {
        boolean status = false;
        if (this.water > minWater) {
            if (this.milk > minMilk) {
                if (this.beans > minBeans) {
                    if (this.cups > 1) {
                        System.out.println("I have enough resources, making you a coffee!");
                        status = true;
                    } else {
                        System.out.println("Sorry, not enough cups!");
                    }
                } else {
                    System.out.println("Sorry, not enough beans!");
                }
            } else {
                System.out.println("Sorry, not enough milk!");
            }
        } else {
            System.out.println("Sorry, not enough water!");
        }

        System.out.println();
        return status;
    }

    private boolean isOn() {
        return this.machineOn;
    }

    private void buyCoffee(int choice) {
        switch (choice) {
            case 1: // espresso
                if (isEnoughResources(250, 0, 16)) {
                    this.water -= 250;
                    this.beans -= 16;
                    this.cups -= 1;
                    this.money += 4;
                }
                break;
            case 2: // latte
                if (isEnoughResources(350, 75, 20)) {
                    this.water -= 350;
                    this.milk -= 75;
                    this.beans -= 20;
                    this.cups -= 1;
                    this.money += 7;
                }
                break;
            case 3: // cappuccino
                if (isEnoughResources(200, 100, 12)) {
                    this.water -= 200;
                    this.milk -= 100;
                    this.beans -= 12;
                    this.cups -= 1;
                    this.money += 6;
                }
                break;
            default:
                break;
        }
    }

    private void fillMachine(int addWater, int addMilk, int addBeans, int addCups) {
        this.water += addWater;
        this.milk += addMilk;
        this.beans += addBeans;
        this.cups += addCups;
    }

    private void takeMoney() {
        System.out.println("I gave you $" + this.money);
        System.out.println();
        this.money = 0;
    }

    private void turnOff() {
        this.machineOn = false;
    }
}