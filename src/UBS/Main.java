package UBS;

import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args){
         
        Console console = System.console();
        ArrayList<BankAccount> accounts = new ArrayList<>();
        float amt;

        System.out.println("Hi, how can I help you today?");

        while (true) { 
            
            String[] text = console.readLine("> ").toLowerCase().split(" "); 

            switch(text[0].toLowerCase()){

                case "new":
                BankAccount account = new BankAccount(text[1]);
                accounts.add(account);
                account.setIsOpen(true);
                if(text.length > 2 ){
                    account.setAccBal(Float.parseFloat(text[2]));
                    System.out.printf("new account created for %s. Acc no is %s, created on %s. Acc bal: %s\n", text[1], account.getAccNo(), account.getOpenDate(), account.getAccBal());
                }else {
                    System.out.printf("new account created for %s. Acc no is %s, created on %s. Acc bal: %s\n", text[1], account.getAccNo(), account.getOpenDate(), account.getAccBal());
                }
                break;

                case "deposit":
                System.out.println("Please enter the according to the format: <deposit amt> <acc number>");
                text = console.readLine().split(" ");
                for (BankAccount b : accounts){
                    if (b.getAccNo().equalsIgnoreCase(text[1])){
                        amt = Float.parseFloat(text[0]);
                        b.deposit(amt);
                    }
                }
                
                break;

                case "withdraw":
                System.out.println("Please enter the according to the format: <withdraw amt> <acc number>");
                text = console.readLine().split(" ");
                for (BankAccount b : accounts){
                    if (b.getAccNo().equalsIgnoreCase(text[1])){
                        amt = Float.parseFloat(text[0]);
                        b.withdraw(amt);
                    }
                }
                break;
               
                case "list":
                System.out.println("Please enter your bank acc number");
                text = console.readLine().split(" ");
                for (BankAccount b : accounts){
                    if (b.getAccNo().equalsIgnoreCase(text[0])){
                        b.list();
                    }
                }
                break;
            }
        }
    }
}
