package org.yearup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OnlineStoreApp {
    private HashMap<String, Products> inventoryMap = new HashMap<String, Products>();
    private ArrayList<Products> cart = new ArrayList<Products>();

    public OnlineStoreApp() {
        String inventoryFilePath;
        loadInventory();


    }

    public void loadInventory() {
        try {
            Scanner scanner = new Scanner(new File("OnlineStoreData/inventory.csv"));
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("\\|");


                String id = line[0];
                String name = line[1];
                double price = Double.parseDouble(line[2]);
                Products product = new Products(id, name, price);
                inventoryMap.put(id, product);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found.");
        }
    }

    public void addToCart(int productId) {
        Products products = inventoryMap.get(productId);
        if (products != null) {
            cart.add(products);
            System.out.println("Added " + products.getName() + " to cart.");
        } else {
            System.out.println("Invalid product ID.");
        }
    }

    public void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your cart:");
            for (Products products : cart) {
                
                System.out.println(products.toString());
            }
        }
    }




}



