package Bank;

import java.io.Console;
import java.lang.classfile.Signature;

public class BAMain {
    
    public static void main(String[] args) {
        
        //BankAccount Jon = new BankAccount("Jon", 2000);
        //System.out.printf("%s, %f", Jon.getNAME(), Jon.getAccBal());

        Console cons = System.console();
        String cmd = cons.readLine("What would you like to do today?\n ").trim();
        String[] input = cmd.split(" ");

        while (true){
            switch (input[0].toLowerCase()) {

                case "open":
                BankAccount obj = new BankAccount(input[1]);
                break;

                case "deposit":
                this.BankAccount
                break;

                case "withdraw":
                break;

                case "balance":
                break;

                case "close"
            }
        }
    }
}
