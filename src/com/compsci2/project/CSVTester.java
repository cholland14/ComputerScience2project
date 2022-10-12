package com.compsci2.project;

import java.io.File;
import java.util.ArrayList;

public class CSVTester {

    public static void main(String[] args) {

        //InventoryCSVFile should contain data in this order
        //itemId, itemName, quantityOnHand, costExpenditure,
        //commas should follow the end of every element (including the last in the row)
        //there should be no blank lines at the end of the file
        String path0 = "C:\\Users\\cgoin\\Documents\\Developer\\ComputerScience2Project\\inventory.txt";
        File inventoryFile = new File(path0);
        ArrayList<Stock> inventory = new ArrayList<>();
        IMS inventoryMS = new IMS(inventory, inventoryFile);

        for(Stock item : inventory) System.out.println(item);

        //IMS must be closed to update CSV files
        inventoryMS.close();
    }
}
