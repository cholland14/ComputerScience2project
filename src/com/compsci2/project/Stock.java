package com.compsci2.project;

public class Stock implements Comparable<Stock> {

    private static int stockCount = 0;
    private String itemId;
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

    public int getNumericalItemId() {
        int numericalItemId = Integer.parseInt(itemId.substring(5));
        return numericalItemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String newItemId) {
        stockCount--;
        itemId = newItemId;
    }

    private String generateItemId() {
        //getNumericalItemId requires a 5 character string here
        stockCount++;
        return "ITEM-" + stockCount;
    }

    public void useItems(int amountUsed) {
        quantity -= amountUsed;
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
        String otherId = o.getItemId();
        return otherId.compareTo(getItemId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Stock)
            return ((Stock) obj).getItemId().equals(itemId);
        else return false;
    }
}
