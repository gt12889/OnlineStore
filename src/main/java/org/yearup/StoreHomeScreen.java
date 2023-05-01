package org.yearup;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

public class StoreHomeScreen {
    private Scanner scanner;
    private static final HashMap<String, Products> inventory = new HashMap<>();
    private static final HashMap<Products, Integer> cart = new HashMap<>();

    public void run()
        {
        StoreHomeScreen();
        displayProducts();


    }
    public void StoreHomeScreen() {
        scanner = new Scanner(System.in);
        // inventory = new Inventory();
        Inventory.loadInventory("OnlineStoreData/inventory.csv");
        //cart = new Cart();
    }

    public void displayProducts() {
        String input = "";
        while (!input.equals("X")) {
            System.out.println("\nWelcome to the Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");

            input = scanner.nextLine();
            switch (input) {
                case "1":
                    showProducts();
                    break;
                case "2":
                    displayCart();
                    break;
                case "3":
                    System.out.println("Thank you for shopping with us!");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    private void showProducts() {
        System.out.println("\nOur Products:");
        Inventory.displayProducts();

        String input = "";
        while (!input.equals("X")) {
            System.out.println("\nEnter product ID to add to cart or 'X' to go back to home screen:");
            input = scanner.nextLine();
            if (inventory.containsKey(input)) {
                Inventory.displayProducts();
//                cart.equals(products);
                cart.put(inventory.get(input),1);
                //System.out.println(products.getName() + " added to cart.");
            } else if (!input.equals("X")) {
                System.out.println("Invalid product ID. Please try again.");
            }
        }
    }

    private void displayCart() {
        System.out.println("\nYour Cart:");
        for (Products products : cart.keySet()) {
            System.out.println(products.getName());
        }


        //cart.displayCart();

        String input = "";
        do {
            System.out.println("\nEnter 'C' to check out or 'X' to go back to home screen:");
            input = scanner.nextLine();
            switch (input) {
                case "C":
                    checkOut();
                    break;
                case "X":
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        } while (!input.equals("X"));


    }

    private void checkOut() {
        double total = 0;
        System.out.printf("\nTotal Amount: $%.2f\n", total);

        double payment = 0.0;
        boolean paidEnough = false;
        while (!paidEnough) {
            System.out.println("\nEnter payment amount:");
            try {
                payment = scanner.nextDouble();
                scanner.nextLine();
                if (payment < total) {
                    System.out.println("Insufficient payment. Please try again.");
                } else {
                    paidEnough = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }

        double change = Math.floor((payment - total) * 100) / 100;
        System.out.printf("Change: $%.2f\n", change);

        System.out.println("\nItems Sold:");
        displayCart();
    }

    private class Inventory {
        public static void loadInventory(String s) {
            try {
                Scanner reader = new Scanner(new File("OnlineStoreData/inventory.csv"));
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] split = line.split("\\|");
                    String id = split[0];
                    String name = split[1];
                    double price = Double.parseDouble(split[2]);

                    Products products = new Products(id, name, price);
                    inventory.put(String.valueOf(id), products);
                }

            } catch (FileNotFoundException e) {
                System.out.println("Error input");
            }
        }

        public static void displayProducts() {

            for (Products products : inventory.values()) {
                System.out.println(products.getId());
            }
        }

        public boolean hasProducts(int i) {
            return false;
        }
    }
}

//    private class Cart {
//        public void displayCart()
//        {
//            System.out.println("Items in your cart:");
//            double total = 0;
//            for Products products: cart)
//            {
//
//            }
//        }

//        public void clearCart()
//        {
//
//        }
//
//        public double calculateTotal()
//        {
//            return 0;
//        }
//    }


//public class Cart
//{
//    private List<Products>items;
//    public Cart()
//    {
//        items = new ArrayList<>();
//    }
//    public void addItem(Products product)
//    {
//        items.add(product);
//    }
//    public void displayCart()
//    {
//        for(Products item: items)
//        {
//            System.out.println("Item Id: " + item.getId() + "\t Name: "+ item.getName() + ("\t\t\t Price: "+ item.getPrice()));
//
//        }
//
//    }
//}
