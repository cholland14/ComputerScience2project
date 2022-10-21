
package com.mycompany.cpsc2project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cecil
 */
public class LoginCheck {
    
    public static void tryLogin (ArrayList<User> users) {
        Scanner in = new Scanner(System.in);
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Enter your username: ");
            String username = in.next();
            System.out.println("Enter your password: ");
            String password = in.next();
            loggedIn = LoginCheck.isLoggedin(username, password, users);
        }
    }
    
    private static boolean isLoggedin(String username, String password, ArrayList<User> users) {

        for (User user : users) {
            if (user.usernameExists(username, users))
            {
                if (user.correctPassword(username, password, users)) {
                    System.out.print("Login sucessful");
                    return true;
                }
                else {
                    System.out.println("Incorrect Password");
                    break;
                }
            } 
            else {
                System.out.println("Incorrect username");
                break;
            }
                
            
        }
     return false;   
    }
}
