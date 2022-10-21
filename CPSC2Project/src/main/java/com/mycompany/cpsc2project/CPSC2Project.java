
package com.mycompany.cpsc2project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cecily holland
 */
public class CPSC2Project {

    public static void main(String[] args) {
     /**
         * user class object
         */
        Scanner in = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("cgoins0125", "123456"));
        users.add(new User("Cec", "12"));
        
        LoginCheck.tryLogin(users);
    }   
        
}

