package com.compsci2.project;

import java.io.File;
import java.util.ArrayList;

public class CSVTester {

    public static void main(String[] args) {

        //File name can be a user input in a while loop until a file is created (or there is no exception) the while loop cannot be broken out of

        //InventoryCSVFile should contain data in this order
        //itemId, itemName, quantityOnHand, costExpenditure, salePrice,
        //commas should follow the end of every element (including the last in the row)
        //there should be no empty lines at the end of the CSV file
        //When IMS closes it will write the CSV files in such a way that satisfies these requirements

        String fileName0 = "inventory.txt"; //when run from command line arg[0] - "inventory.txt"
        String fileName1 = "sales.txt"; //when run from command line arg[1] - "sales.txt"
        String receiptPath = "C:\\Users\\cgoin\\Documents\\Developer\\ComputerScience2Project\\receipts";
        File inventoryFile = new File(fileName0);
        File salesFile = new File(fileName1);
        ArrayList<Stock> inventory = new ArrayList<>();
        ArrayList<Sale> sales = new ArrayList<>();
        IMS inventoryMS = new IMS(inventory, inventoryFile, sales, salesFile, receiptPath);

                //insert test commands here ie add stock, add sale.

        int[][] sale1 = {{1,3}};
        sales.add(new Sale(1,sale1));

        //IMS must be closed to update CSV files -- if we implement AutoCloseable we can surround in a try-with-resources block
        inventoryMS.close();
    }
}
