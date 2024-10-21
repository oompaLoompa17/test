package UBS;

import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args){

        // BankAccount ba = new BankAccount("bob");
        // System.out.println("my name is " + ba.getName() + " and my acc no is " + ba.getAccNo());
        // System.out.println("I created this account on " + ba.getOpenDate());
        
        Console console = System.console();
        ArrayList<BankAccount> accounts = new ArrayList<>();
        float amt;

        while (true) { 
            
            String[] text = console.readLine().toLowerCase().split(" "); 

            if (text[0].equalsIgnoreCase("new")){
                BankAccount account = new BankAccount(text[1]);
                accounts.add(account);
    
                }else if (text[0].equalsIgnoreCase("deposit")){
                    amt =Float.parseFloat(text[1]);
                   // account.deposit(amt);

                }else if (text[0].equalsIgnoreCase("withdraw")) {
                    amt = Float.parseFloat(text[1]);
                    //account.withdraw(amt);
                }
            

            // switch(text[0]){

            //     case "new":
            //     BankAccount ba = new BankAccount(text[1]);
            //     break;

            //     case "deposit":
            //     float amt = Float.parseFloat(text[1]);
            //     ba.deposit(amt);
            //     break;

            //     case "withdraw":
            //     amt = Float.parseFloat(text[1]);
            //     ba.add(amt);
            //     break;

                
            // }
        }
    }
}
