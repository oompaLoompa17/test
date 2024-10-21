package UBS;

import java.io.Console;
import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) {
        Console console = System.console();

        // Check if console is available
        if (console == null) {
            System.out.println("No console available. Please run this program in a console.");
            return;
        }

        ArrayList<BankAccount> accounts = new ArrayList<>();
        //QN: how does this work when i've alr overridden the default constructor to take in at least 1 param
        BankAccount currentAccount = null; // To keep track of the currently selected account

        while (true) {
            String input = console.readLine("Enter command: ").toLowerCase();
            String[] text = input.split(" ");

            switch (text[0]) {
                case "new" -> {
                    // Create a new account
                    if (text.length < 2) {
                        console.printf("Usage: new <name>\n");
                        break;
                    }
                    BankAccount account = new BankAccount(text[1]);
                    accounts.add(account);
                    console.printf("Account created for %s with account number %s.\n", account.getName(), account.getAccNo());
                }

                case "select" -> {
                    // Select an account
                    if (text.length < 2) {
                        console.printf("Usage: select <account number>\n");
                        break;
                    }
                    String accNo = text[1];
                    currentAccount = accounts.stream()
                            .filter(acc -> acc.getAccNo().equals(accNo))
                            .findFirst()
                            .orElse(null);
                    if (currentAccount != null) {
                        console.printf("Selected account: %s\n", currentAccount.getAccNo());
                    } else {
                        console.printf("Account not found.\n");
                    }
                }

                case "deposit" -> {
                    // Deposit money into the selected account
                    if (currentAccount == null) {
                        console.printf("No account selected. Please select an account first.\n");
                        break;
                    }
                    if (text.length < 2) {
                        console.printf("Usage: deposit <amount>\n");
                        break;
                    }
                    try {
                        float depositAmount = Float.parseFloat(text[1]);
                        currentAccount.deposit(depositAmount);
                        console.printf("Deposited $%.2f. New balance: $%.2f\n", depositAmount, currentAccount.getAccBal());
                    } catch (NumberFormatException e) {
                        console.printf("Invalid amount.\n");
                    }
                }

                case "withdraw" -> {
                    // Withdraw money from the selected account
                    if (currentAccount == null) {
                        console.printf("No account selected. Please select an account first.\n");
                        break;
                    }
                    if (text.length < 2) {
                        console.printf("Usage: withdraw <amount>\n");
                        break;
                    }
                    try {
                        float withdrawAmount = Float.parseFloat(text[1]);
                        currentAccount.withdraw(withdrawAmount);
                        console.printf("Withdrew $%.2f. New balance: $%.2f\n", withdrawAmount, currentAccount.getAccBal());
                    } catch (NumberFormatException e) {
                        console.printf("Invalid amount.\n");
                    } catch (IllegalArgumentException e) {
                        console.printf(e.getMessage() + "\n");
                    }
                }

                case "exit" -> {
                    // Exit the program
                    console.printf("Exiting the program.\n");
                    return;
                }

                default -> console.printf("Unknown command. Please try again.\n");
            }
        }
    }
}
