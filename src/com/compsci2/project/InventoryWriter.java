package com.compsci2.project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class InventoryWriter {

    private FileWriter output;

    public InventoryWriter(ArrayList<Stock> inventory, String fileName) throws IOException {
        output = new FileWriter(fileName);
        setText(inventory);
    }

    private void setText(ArrayList<Stock> inventory) throws IOException {
        for (Stock item : inventory)  {
            output.write(item.toString()+"\n");
        }
            output.close();
    }
}
