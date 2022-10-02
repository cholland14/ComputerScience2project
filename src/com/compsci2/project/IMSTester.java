package com.compsci2.project;

import java.util.ArrayList;

public class IMSTester {
    public static void main(String[] args) {


        // FOR TESTING  PURPOSES

        ArrayList<Stock> inventory = new ArrayList<>();
        inventory.add(new Stock("carrot", 5, 9));
        inventory.add(new Stock("apple", 2, 1));
        inventory.add(new Stock("banana", 10, 5));
        inventory.add(new Stock("flour", 6, 2));

        ArrayList<Carburetor> carburetors = new ArrayList<>();
        int[][] carb1Stock = {{1,3},{2,1},{3,2}};
        carburetors.add(new Carburetor("carb1",4,carb1Stock,inventory));

        //displays current inventory and carburetors
        for (Stock item : inventory) System.out.println(item);
        for (Carburetor carb : carburetors) System.out.println(carb);

        //Displays items required to build one new carburetor
        System.out.println("\n" + carburetors.get(0).getItemsRequiredToMake());

        //builds a carburetor and displays a message about its success
        System.out.println(carburetors.get(0).buildCarb(1));
        System.out.println("\n");

        //prints new inventory to prove that necessary changes have been made
        for (Stock item : inventory) System.out.println(item);
        for (Carburetor carb : carburetors) System.out.println(carb);
    }
}
