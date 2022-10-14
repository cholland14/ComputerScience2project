package com.compsci2.project;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Sale {

    private static final double TAX_AMOUNT = 0.09;
    private static File receiptPath;
    private static ArrayList<Stock> inventory;
    private static int saleCount = 0; //we want this to update at the start of the program.
    private static String date; //based on date at program start, not when the date changes
    private static DecimalFormat df;
    private int receiptId;
    private int customerID;
    private int[][] soldItemId_quantity;

    //this constructor should be called when the IMS is opened to initialize the Sales class
    //the static variables need to be set before sales start
    public Sale (String receiptPath, ArrayList<Stock> inventory, int saleCount) {
        this.receiptPath = organizeReceipts(receiptPath);
        this.inventory = inventory;
        date = getDate();
        //subtracting one because it gets added back when the next sale is completed
        this.saleCount = saleCount-1;
        df = new DecimalFormat("#.##");
    }

    //Should we implement the check for saleable in another way?
    public Sale (int customerId, int[][] soldItemId_quantity) {
        this.soldItemId_quantity = soldItemId_quantity;
        if (saleable() == true) {
            updateStock();
            this.receiptId = generateReceiptId();
            this.customerID = customerId;
            printReceipt();
        }
        else System.out.println("There are not enough resources to complete sale");
    }

    //This is a file organization method for receipts
    //Specified dir is given at program start
    //if the folders has not already been created, then this will create them
    //Organization: receipts\year\month\date
    private File organizeReceipts(String receiptPath) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        File addReceipts = new File(receiptPath);
        if (!addReceipts.exists()) addReceipts.mkdir();
        File addYear = new File(receiptPath+"\\"+year);
        if (!addYear.exists()) addYear.mkdir();
        File addMonth = new File(addYear.toString()+"\\"+month);
        if (!addMonth.exists()) addMonth.mkdir();
        File addDay = new File(addMonth.toString()+"\\"+day);
        if (!addDay.exists())addDay.mkdir();
        return addDay;
    }

    //checks that there are enough resources to initiate the sale
    private boolean saleable() {
        int itemId = 0;
        int quantity = 1;
        for (int i = 0; i < soldItemId_quantity.length; i++) {
            itemId = soldItemId_quantity[i][0];
            quantity = soldItemId_quantity[i][1];
            for (Stock item : inventory) {
                if (itemId == item.getItemId()) {
                    //returns false if there is an item in the sale not in the inventory
                    if (item.getQuantityOnHand() < quantity) return false;
                }
            }
        }
        //returns true once all items have been checked
        return true;
    }

    //calculates how much is invested in the items to be sold
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
        double formattedTotal = Double.parseDouble(df.format(total));
        return formattedTotal;
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
        double profit = (getSubtotal() - getCostExpenditure());
        return profit;
    }

    private double getSubtotal() {
        int itemId = 0;
        int quantitySold = 0;
        double subtotal = 0;
        for (int i = 0; i < soldItemId_quantity.length; i++) {
            itemId = soldItemId_quantity[i][0];
            quantitySold = soldItemId_quantity[i][1];
            for (Stock item : inventory) {
                if (itemId == item.getItemId()) {
                    subtotal += (item.getSalePrice() * quantitySold);
                    break;
                }
            }
        }
        double formattedSubtotal = Double.parseDouble(df.format(subtotal));
        return formattedSubtotal;
    }

    private double getTax() {
        double tax = getSubtotal() * TAX_AMOUNT;
        double formattedTax = Double.parseDouble(df.format(tax));
        return formattedTax;
    }

    private double getTotal() {
        double total = (getSubtotal() + getTax());
        return total;
    }

    private int generateReceiptId() {
        saleCount++;
        return saleCount;
    }

    private String getDate() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return month + "/" + day + "/" + year;
    }

    public String getItemsSold() {
        String requiredItems = "Item ID | Item Name | Quantity Sold | Sale Price \n";
        int itemId = 0;
        int quantitySold = 0;
        for (int i = 0; i < soldItemId_quantity.length; i++) {
            itemId = soldItemId_quantity[i][0];
            quantitySold = soldItemId_quantity[i][1];
            for (Stock item : inventory) {
                if (itemId == item.getItemId()) {
                    requiredItems += itemId + " | " + item.getItemName().toUpperCase() + " | " + quantitySold + " | " + item.getSalePrice() + "\n";
                    //breaks out of for loop once item is found
                    break;
                }
            }
        }
        return requiredItems;
    }

    private String getReceiptFormat() {
        int itemId = 0;
        int quantitySold = 0;
        String receiptLine = "";
        for (int i = 0; i < soldItemId_quantity.length; i++) {
            itemId = soldItemId_quantity[i][0];
            quantitySold = soldItemId_quantity[i][1];
            for (Stock item : inventory) {
                if (itemId == item.getItemId()) {
                    receiptLine += quantitySold+" "+item.getItemName().toUpperCase() + "               " + (item.getSalePrice() * quantitySold) + "\n";
                    //breaks out of for loop once item is found
                    break;
                }
            }
        }
        return receiptLine;
    }

    //prints a receipt to a folder called receipts -- keep a copy for records
    //print a copy for customer
    //Should I use a PrintWriter instead of a FileWriter?
    public void printReceipt() {
        String fileName = "Receipt"+receiptId+".txt";
        try (FileWriter fw = new FileWriter(new File(receiptPath, fileName))) {
            fw.write(getReceiptFormat()+"\n"+
                    "Subtotal: " + getSubtotal()+"\n"+
                    "Tax: " + getTax()+"\n"+
                    "Total: " + getTotal());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return  receiptId + ","
                + customerID + ","
                + getSubtotal() + ","
                + getTax() + ","
                + getTotal() + ","
                + getCostExpenditure() + ","
                + getProfit() + ","
                + date + ',';
    }
}
