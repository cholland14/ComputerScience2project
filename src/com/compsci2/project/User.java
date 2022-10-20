
package com.mycompany.userinterface;

import java.util.Scanner;


/**
 *
 * @author cecily holland
 * user class to store data sets userName, password, adminKey, isAdmin variables
 * 
 * @userName
 * @adminKey
 * @password
 * @isAdmin
 * @userType;
 */
public class User {

    private String userName;
    private static String adminKey;
    private String password;
    private boolean isAdmin = false;
    
    public User(String userName, String password, String adminKey){
        this.userName = userName;
        this.password = password;
        this.adminKey = adminKey;
                
    }
    
    /**
     * 
     * @return userName
     */
    public String getUsername(){
        return this.userName;
    }
   
    /**
     * 
     * set user password for new user and display for them the password
     */
    public void setPassword(String password){
        password = this.password;
    }

 /**
     * @param generatedString 
     * @return password
     */
    public String getPassword(String password){
        return this.password;
        
    }
    /**
     * Static method to set adminKey
     * @param userName

     */
    public static void setAdminKey(String userName){
        adminKey = "1234567";
    }
    
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
