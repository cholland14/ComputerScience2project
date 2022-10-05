package com.compsci2.project;

import java.util.ArrayList;
import java.util.Date;

public class Sale {

    private static int saleCount = 0;
    private static final double TAX_AMOUNT = 0.09;
    private final int receiptId;
    private double salePrice;
    private final int[][] soldItemId_quantity;
    private ArrayList<Stock> inventory;
    private final Date sellDate;
    private int customerID;

    public Sale (int customerId, int[][] soldItemId_quantity, ArrayList<Stock> inventory, double salePrice) {
        this.soldItemId_quantity = soldItemId_quantity;
        this.inventory = inventory;
        if (Saleable() == false) {
            throw new IllegalArgumentException("There are not enough items in the inventory to complete sale!");
        }
        this.salePrice = salePrice;
        this.receiptId = generateReceiptId();
        this.customerID = customerId;
        updateStock();
        sellDate = new Date();
    }

    private boolean Saleable() {
        int itemId = 0;
        int quantity = 1;
        for (int i = 0; i < soldItemId_quantity.length; i++) {
            itemId = soldItemId_quantity[i][0];
            quantity = soldItemId_quantity[i][1];
            for (Stock item : inventory) {
                if (itemId == item.getItemId()) {
                    //returns false if there is an item in the sale not in the inventory
                    if (item.getQuantityOnHand() < quantity) return false;
                //breaks the for loop once item is found
                break;
                }
            }
        }
        //returns true once all items have been checked
        return true;
    }

    //calculates how much is invested in the sale items
    private double getCostExpenditure() {
        int quantity = 1;
        double total = 0;
        double itemPrice = 0;
        int itemId = 0;
        for (int i = 0; i < soldItemId_quantity.length; i++) {
            itemId = soldItemId_quantity[i][0];
            quantity = soldItemId_quantity[i][1];
            //compare itemId to inventory to get price. Multiply price by
            // quantity and sum to total.
            for (Stock item : inventory) {
                if (itemId == item.getItemId()) {
                    itemPrice = item.getCostExpenditure();
                    //breaks the for loop once item is found
                    break;
                }
            }
            total += (itemPrice*quantity);
        }
        return total;
    }

    //Sales the items requested by removing stock sold
    private void updateStock() {
        int saleQuantity = 0;
        int itemId = 0;
        int index = 0;
        for (int i = 0; i < soldItemId_quantity.length; i++) {
            itemId = soldItemId_quantity[i][0];
            saleQuantity = soldItemId_quantity[i][1];
            for (Stock item : inventory) {
                if (itemId == item.getItemId()) {
                    index = inventory.indexOf(item);
                    //Decreases stock by the amount of resources sold
                    inventory.get(index).useItems(saleQuantity);
                    //once item is found the rest of inventory need not be searched through
                    break;
                }
            }
        }
    }

    private double getProfit() {
        return salePrice - getCostExpenditure();
    }

    private double getTax() {
        return salePrice * TAX_AMOUNT;
    }

    private double getTotalWithTax() {
        return salePrice + getTax();
    }

    private int generateReceiptId() {
        saleCount++;
        return saleCount;
    }

    public String itemsSold() {
        String requiredItems = "Item ID | Item Name | Quantity Sold \n";
        int itemId = 0;
        int quantitySold = 0;
        for (int i = 0; i < soldItemId_quantity.length; i++) {
            itemId = soldItemId_quantity[i][0];
            quantitySold = soldItemId_quantity[i][1];
            for (Stock item : inventory) {
                if (itemId == item.getItemId()) {
                    requiredItems += itemId + " | " + item.getItemName().toUpperCase() + " | " + quantitySold + "\n";
                    //breaks out of for loop once item is found
                    break;
                }
            }
        }
        return requiredItems;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "receiptId=" + receiptId +
                ", customerID=" + customerID +
                ", salePrice=$" + salePrice +
                ", salesTax=$" + getTax() +
                ", totalSale=$" + getTotalWithTax() +
                ", costExpenditure=$" + getCostExpenditure() +
                ", profit=$" + getProfit() +
                ", sellMade=" + sellDate +
                '}';
    }
}
