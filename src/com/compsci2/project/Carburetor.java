package com.compsci2.project;

import java.util.ArrayList;
import java.util.Arrays;

public class Carburetor {

    private static int count = 0;
    private int[][] requiredStock;
    private String carbId;
    private String carbName;
    private int quantity;

    //Array should store items from stock that are necessary to build carburetor
    //first column should contain itemId and second column should contain quantity needed
    public Carburetor(String carbName, int[][] itemId_quantity) {
        requiredStock = itemId_quantity;
        this.carbName = carbName;
        carbId = generateCarbId();
    }

    //returns how many carburetors of this type can be made with given inventory
    public int ableToMake(ArrayList<Stock> inventory) {
        //need variables for calculations
        int neededQuantity = 0;
        int itemId = 0;
        int inventoryQuantity = 0;
        int[] values = new int[requiredStock.length];
        for (int i = 0; i < requiredStock.length; i++) {
            itemId = requiredStock[i][0];
            neededQuantity = requiredStock[i][1];
            for (Stock item : inventory) {
                if (itemId == item.getNumericalItemId()) {
                    inventoryQuantity = item.getQuantityOnHand();
                    values[i] = inventoryQuantity/neededQuantity;
                }
            }
        }
        Arrays.sort(values);
        return values[0];
    }

    //removes stock needed to build the carburetor
    public void buildCarb(int quantity, ArrayList<Stock> inventory) {
        if (quantity <= ableToMake(inventory)) {
            this.quantity += quantity;
            int neededStockQuantity = 0;
            int itemId = 0;
            int index = 0;
            for (int i = 0; i < requiredStock.length; i++) {
                itemId = requiredStock[i][0];
                neededStockQuantity = requiredStock[i][1];
                for (Stock items : inventory) {
                    if (itemId == items.getNumericalItemId()) {
                        index = inventory.indexOf(items);
                    }
                }
                //Decreases stock by the amount of resources required to build the carbs
                inventory.get(index).useItems(neededStockQuantity*quantity);
            }
        }
        else System.out.println("Not enough resources to buildCarb!!!");
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private String generateCarbId() {
        count++;
        return "CARB-" + count;
    }

    public int getNumericalCarbId() {
        int numericalCarbId = Integer.parseInt(carbId.substring(5));
        return numericalCarbId;
    }

    public double costExpenditure(ArrayList<Stock> inventory) {
        double total = 0;
        int quantity = 1;
        int itemId = 0;
        double itemPrice = 0;
        for (int i = 0; i < requiredStock.length; i++) {
            itemId = requiredStock[i][0];
            quantity = requiredStock[i][1];
            //compare itemId to inventory to get price. Multiply price by
            // quantity and sum to total.
            for (Stock item : inventory) {
                if (itemId == item.getNumericalItemId())
                    itemPrice = item.getCostExpenditure();
            }
            total += (itemPrice*quantity);
        }
        return total;
    }

    @Override
    public String toString() {
        return "Carburetor{" +
                "carbId='" + carbId + '\'' +
                ", carbName='" + carbName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
