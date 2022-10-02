package com.compsci2.project;

public class Stock implements Comparable<Stock> {

    private static int stockCount = 0;
    private int itemId;
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
        //getNumericalItemId requires a 5 character string here
        stockCount++;
        Integer intId = (Integer) stockCount;
        String stringId = intId.toString();
        String stringReturnId = "";
        for (int i =0 ; i<8- stringId.length(); i++) {
            stringReturnId += 0;
        }
        stringReturnId += stringId;
        int returnId = Integer.parseInt(stringReturnId);
        return returnId;
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
