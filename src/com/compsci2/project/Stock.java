package com.compsci2.project;

public class Stock {

    private static int stockCount = 0;
    private String itemId;
    private String itemName;
    private int quantity;
    private double costExpenditure;

    public Stock(String itemName, double costExpenditurePerItem, int quantityOnHand) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.costExpenditure = costExpenditurePerItem;
        itemId = generateItemId();
    }

    public int getQuantityOnHand() {
        return quantity;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantity = quantityOnHand;
    }

    public void useItems(int amountUsed) {
        quantity -= amountUsed;
    }

    private String generateItemId() {
        //Important to not change "ITEM-" due to dependency
        //in getNumericalItemId
        stockCount++;
        return "ITEM-" + stockCount;
    }

    public int getNumericalItemId() {
        int numericalItemId = Integer.parseInt(itemId.substring(5));
        return numericalItemId;
    }

    public String getItemId() {
        return itemId;
    }

    public double getCostExpenditure() {
        return costExpenditure;
    }

    public void setCostExpenditure(double costExpenditure) {
        this.costExpenditure = costExpenditure;
    }

    @Override
    public String toString() {
        return "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantityOnHand=" + quantity +
                ", costExpenditure=" + costExpenditure +
                '}';
    }
}
