package com.compsci2.project;

import java.util.ArrayList;
import java.util.LinkedList;

public class IMSTester {
    public static void main(String[] args) {


        // FOR TESTING  PURPOSES

        int[][] carb2stock = {{3,1},{4,3}};
        ArrayList<Stock> inventory = new ArrayList<>();
        inventory.add(new Stock("carrot", 5, 9));
        inventory.add(new Stock("apple", 2, 1));
        inventory.add(new Stock("banana", 10, 5));
        inventory.add(new Stock("flour", 6, 2));
        inventory.add(new Carburetor("carb2", 1, carb2stock, inventory));

        System.out.println(((Carburetor) inventory.get(4)).getItemsRequiredToMake());

        int[][] carb1Stock = {{1,3},{2,1},{3,2}};
        inventory.add(new Carburetor("carb1",4,carb1Stock,inventory));
        Carburetor carbRef = (Carburetor) inventory.get(5);

        //displays current inventory and inventory
        for (Stock item : inventory) System.out.println(item);

        //Displays items required to build one new carburetor
        System.out.println("\n" + ((Carburetor)inventory.get(5)).getItemsRequiredToMake());

        //builds a carburetor;
        ((Carburetor)inventory.get(5)).buildCarb(1);
        System.out.print("After build: \n");
        //prints new inventory to prove that necessary changes have been made
        for (Stock item : inventory) System.out.println(item);

        int[][] sale1 = {{1,2},{4,1}};
        LinkedList<Sale> sales = new LinkedList<>();
        sales.add(new Sale(1,sale1,inventory,100));
        System.out.println(sales.get(0));

        for (Stock item : inventory) System.out.println(item);
    }
}
