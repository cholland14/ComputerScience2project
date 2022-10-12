package com.compsci2.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IMS {

    //This class is not finished should add functionality for Carburetor, customers, sales, and suppliers

    private ArrayList<Stock> inventory;
    private File inventoryFile;

    public IMS(ArrayList<Stock> inventory, File inventoryFile) {
        this.inventory = inventory;
        this.inventoryFile = inventoryFile;
        initialize();
    }

    //Initializes the inventory management system
    private void initialize() {
        try (Scanner in = new Scanner(inventoryFile)){
            //Delimiter separates values in file based on commas
            in.useDelimiter(",");
            //Necessary to skip the first line of the CSV file which contains the header
            in.nextLine();

            while (in.hasNext()) {
                //stores data from CSV to respective variables
                String itemId = in.next().trim();
                String itemName = in.next().trim();
                String quantityOnHand = in.next().trim();
                String costExpenditure = in.next().trim();

                //changes String representation of itemId to an integer
                int id = Integer.parseInt(itemId);

                //changes String representation of quantityOnHand to an integer
                int quantity = Integer.parseInt(quantityOnHand);

                //changes String representation of costExpenditure to double
                double cost = Double.parseDouble(costExpenditure);

                inventory.add(new Stock(id,itemName,quantity,cost));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Please give a valid CSV path file");
        }
    }

    //When the IMS is closed, it should update all CSV files to prepare for next use.
    public void close() {
        try (FileWriter inventoryWriter = new FileWriter(inventoryFile)){
            inventoryWriter.write("ItemId,ItemName,Quantity,Cost,");
            for (Stock item : inventory) {
                inventoryWriter.write("\n"+item.getItemId()+","+item.getItemName()+","
                        +item.getQuantityOnHand()+","+item.getCostExpenditure()+",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
