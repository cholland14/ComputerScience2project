package com.compsci2.project;

public class Stock implements Comparable<Stock> {

    private static int stockCount = 0;
    private final int itemId;
    private String itemName;
    private int quantity;
    private double costExpenditure;

    public Stock(String itemName, double costExpenditurePerItem, int quantityOnHand) {
        this.itemName = itemName;
        this.quantity = quantityOnHand;
        this.costExpenditure = costExpenditurePerItem;
        itemId = generateItemId();
    }

    public int getQuantityOnHand() {
        return quantity;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantity = quantityOnHand;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getCostExpenditure() {
        return costExpenditure;
    }

    public void setCostExpenditure(double costExpenditure) {
        this.costExpenditure = costExpenditure;
    }

    public int getItemId() {
        return itemId;
    }

    private int generateItemId() {
        stockCount++;
        return stockCount;
    }

    public void useItems(int amountUsed) {
        if (quantity >= amountUsed) quantity -= amountUsed;
        else throw new IllegalArgumentException("There are not enough items in the inventory to use");
    }


    @Override
    public String toString() {
        return "Item{" +
                "itemId= '" + itemId + '\'' +
                ", Name= '" + itemName + '\'' +
                ", Quantity= '" + quantity + '\''+
                ", costExpenditure= '$" + costExpenditure + '\'' +
                '}';
    }

    @Override
    public int compareTo(Stock o) {
        if (this.itemId < o.itemId) return -1;
        else if (this.itemId > o.itemId) return 1;
        else return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Stock)
            return (this.itemId == ((Stock) obj).itemId);
        else return false;
    }
}
