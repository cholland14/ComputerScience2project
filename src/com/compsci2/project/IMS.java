package com.compsci2.project;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IMS implements Closeable {

    //This class is not finished it should add functionality for customers, suppliers, and user interface in the initialize() and close() methods

    private ArrayList<Stock> inventory;
    private ArrayList<Sale> sales;
    private File inventoryFile;
    private File salesFile;
    private String receiptsPath;

    public IMS(ArrayList<Stock> inventory, File inventoryFile, ArrayList<Sale> sales, File salesFile, String receiptsPath) {
        this.inventory = inventory;
        this.sales = sales;
        this.inventoryFile = inventoryFile;
        this.salesFile = salesFile;
        this.receiptsPath = receiptsPath;
        initialize();
    }

    //Initializes the inventory management system
    private void initialize() {
        initializeInventory();
        initializeSales();
    }

    private void initializeSales() {
        int saleCount = 0;
        try (Scanner in = new Scanner(salesFile)) {
            while (in.hasNextLine()) {
                in.nextLine();
                saleCount++;
                System.out.println(saleCount);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Please enter a valid sales CSV file");
        }
        Sale storage = new Sale(receiptsPath, inventory,saleCount);
    }

    private void initializeInventory() {
        try (Scanner in = new Scanner(inventoryFile)) {
            in.useDelimiter(",");
            //Necessary to skip the first line of the CSV file which contains the header
            in.nextLine();
            while (in.hasNext()) {
                try {
                    int itemId = Integer.parseInt(in.next().trim());
                    String itemName = in.next().trim();
                    int quantityOnHand = Integer.parseInt(in.next().trim());
                    double costExpenditure = Double.parseDouble(in.next().trim());
                    double salePrice = Double.parseDouble(in.next().trim());
                    inventory.add(new Stock(itemId, itemName, quantityOnHand, costExpenditure, salePrice));
                } catch (NumberFormatException e) {
                    System.out.println("Please check that the inventory file is formatted correctly");
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Please give a valid CSV file for inventory");
        }
    }


    //the following methods close occur when the IMS is closed


    //When the IMS is closed, it should update all CSV files to prepare for next use.
    //Format: the first line should include information about the stored data,
    //        each field should end with a comma (including the final field of each row)
    //        there should be no empty rows after the last line of data once the file has been written.
    //        this format should be followed for every file type we are writing to
    @Override
    public void close() {
        closeInventory();
        closeSales();
    }

    //currently overwrites the sales file -- need a way to fix this.
    private void closeSales() {
        String fileContents = "";
        try (Scanner in = new Scanner(salesFile)) {
            //don't want the first line if it exists
            if (in.hasNextLine()) in.nextLine();
            while (in.hasNextLine()) {
                fileContents += ("\n"+in.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter salesWriter = new FileWriter(salesFile)) {
            salesWriter.write("receiptId, customerId, subtotal, salesTax, totalSale, costExpenditure, profit, sellMade,");
            salesWriter.write(fileContents);
            for (Sale sale : sales) {
                salesWriter.write("\n" + sale.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private void closeInventory() {
        try (FileWriter inventoryWriter = new FileWriter(inventoryFile)){
            inventoryWriter.write("ItemId,ItemName,Quantity,CostExpenditure,SalePrice,");
            for (Stock item : inventory) {
                inventoryWriter.write("\n"+item.getItemId()+","+item.getItemName()+","
                        +item.getQuantityOnHand()+","+item.getCostExpenditure()+","+item.getSalePrice()+",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
