
package com.mycompany.cpsc2project;

import java.util.ArrayList;




/**
 * 
 * @author cecily holland
 * user class to store data sets userName, password, and checks to make sure that the userName is available
 * and the password is correct
 * 
 * @userName
 * @password

 */
public class User {

    private String userName;
    private String password;

           
    /**
     * constructor with no parameters
     */
    
    /**
     * constructor with parameters
     * @param userName
     * @param password
     */
    public User(String userName, String password ){
        this.userName = userName;
        this.password = password;

    }
    /**
     * sets userName 
     * @param userName
     */
    public void setUserName(String userName){
        this.userName = userName;
    }
    /**
     * gets userName
     * @return userName
     */
    public String getUsername(){
        return this.userName;
    }
   
    /**
     * set user password 
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
        
    }

 /**
     * @param password
     * @return password
     */
    public String getPassword(){
        return this.password;
        
    }
    /**
     * 
     * @param userName
     * @param users
     * @return 
     */
    public boolean usernameExists(String userName, ArrayList<User> users) {
        for (User user : users) {
            if (userName.equalsIgnoreCase(user.getUsername())) {
                return true;
            }
        }
        return false;
    }
    

    public boolean correctPassword(String username, String password, ArrayList<User> users) {
        for (User user : users) {
            if (userName.equalsIgnoreCase(user.getUsername()) && password.equals(user.getPassword())) {
                return true;
            }   
        }
        return false;
    }

///**
// * add suppliers to supplier arrayList 
// */
//    public void addSupplier(){
//         ArrayList<String> supplierList = new ArrayList<String>();
//         supplierList.add<>;
//    }
// /**
//  * delete suppliers from supplier arrayList 
//  */
//    public void deleteSupplier(){
//        System.out.println("Delete a supplier: ");
//        
//        
//    }
///**
// * print Supplier arrayList
//     * @param supplierList
// */
//    public void getSupplierList(ArrayList supplierList){
//        for(int i=0; i < supplierList.size(); i++)
//        System.out.println(supplierList.get(i));
//        
//    }
//    /**
//     * add inventory to inventory arrayList
//     */
//    public void addInventoryItem(){
//        ArrayList<String> InventoryItem = new ArrayList<String>();
//        System.out.println("Add inventory item: ");
//        InventoryItem.add(in.next());
//    }
//    /**alternatively we could have 2-D array hold both
//    *the items in stock as well as the amount of each item
//    *such as: 
//    *but then how would you as the user iterate through the 2-d array 
//    *in order to add inventory items and amounts?
//    */ 
//    public void addInventoryitems(){
//    String[][] Inventory = new String[4][4];
//        for (String[] Inventory1 : Inventory) {
//            System.out.println("Add inventory item: " );
//            Inventory1[0] = in.next();
//            for (int col = 0; col < Inventory1.length; col++) {
//                System.out.println("Add item count: " );
//                Inventory[0][col] = in.next();
//            } 
//        }
//    }
//    /**
//     * delete inventory item
//     */
//    public void deleteInventoryItem(){
//        System.out.println("Delete inventory item: ");
//        
//    }
//    
//    /**
// * print Supplier arrayList
//     * @param InventoryItem
// */
//    public void getInventoryItems(ArrayList InventoryItem){
//        for(int i=0; i < InventoryItem.size(); i++)
//        System.out.println(InventoryItem.get(i));
//        
//    }
//    /**
//     * add inventory stock to inventoryStock arrayList 
//     * how would inventory Items automatically populate inventory stock?
//     * heterogeneous array 
//     */
//    public void addInventoryStock(){
//         List<Integer> InventoryStock = new ArrayList<>();
//         System.out.println("Add inventory stock: ");
//         InventoryStock.add(in.nextInt());
//    }
//
//    
//    /**
//     * delete inventory stock - would need to print current array of inventory, what would you like to delete,
//     * then compare to iteration through the array to find element and then delete it
//     */
//    public void deleteInventoryStock(){
//        
//    }

    




//    /**
//     * Static method to set adminKey
//     */
//    public static void setAdminKey(){
//        adminKey = "1234567";
//    }   
////    /**
////     * Determines if the entered adminKey is correct .
////     * @param adminKey
////     * @return userType
////     */
//    public boolean isAdmin(String adminKey){
//        
//        if(adminKey.equals("1234567")){
//            isAdmin = true;
//    }
//                 
//        return this.isAdmin;
//    }


    
        
    
}
