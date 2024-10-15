package Groceries;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

// add, remove and list the contents of a shopping cart
public class ShoppingCart2 {

    public static void main(String[] args) throws IOException, FileNotFoundException {
        
        // check dir name to write file into
        String dirName;
        if (args.length > 0){
            dirName = args[0]; 
        } else{
            dirName = "db";
        }

        // create new dir if name is not taken alr
        Dir dir = new Dir();
        dir.newDir(dirName);     

        Console cons = System.console();

        // create a hashset to store shoppers
        HashSet<Shopper> users = new HashSet<>();

        System.out.println("Welcome to your shopping cart!");

        while (true) {

            String input = cons.readLine("> ").trim();

            // store command sentence in array
            String[] text = input.split(" "); 
            
            // check array for strings that contain "," and replace with ""
            for (int i=0; i < text.length; i++){
                text[i] = text[i].replace(",","");
            }

            // help how do i instantiate new users??
            if (text[0].equalsIgnoreCase("login")){
                String username = text[1];
                
                // Check if the user is already logged in
                boolean userExists = users.stream().anyMatch(shopper -> shopper.getName().equals(username));
                
                if (userExists) {
                    System.out.printf("%s is already logged in.\n", username);
                    // Optionally, handle listing the user's cart here
                } else {
                    // Create a new Shopper
                    Shopper shopper = new Shopper(username);
                    users.add(shopper);  // Add the new shopper to the HashSet

                    // Optionally create the user's grocery list file
                    File glist = new File(dirName + "/" + username + ".txt");
                    glist.createNewFile();

                    System.out.printf("Welcome, %s! You have been logged in.\n", username);
                }
                continue; // Continue to the next loop iteration
            }
            

            switch (text[0].toLowerCase()) {

                case "list":
                    shopper.list();
                    break;

                case "add":            
                    for (String s: text){
                        // omit the "add" from the command line check
                        if (s.equals("add")) {
                            continue;
                        }
                        shopper.add(s);
                    }
                    break;

                case "delete":
                    int itemdx = Integer.parseInt(text[1]);
                    shopper.delete(itemdx);

                case "save":
                    // output stream
                    FileWriter fw = new FileWriter(dirName);
                    BufferedWriter bw = new BufferedWriter(fw);
                    
                break;

                // case "login":
                //     String user = text[1];

                //     File glist = new File(dirName + "/" + text[1] + ".txt");
                //     BufferedInputStream br = new BufferedInputStream(new FileInputStream(glist));

                //     // user exists
                //     if (users.contains(user)){
                //         // and list is empty
                //         if (groceries.isEmpty()){
                //             System.out.printf("%s, your cart is empty.", user);
                //         // and list has items
                //         } else{
                //             System.out.printf("%s, your cart contains the following items\n", user);
                //             for (int i=1; i <= groceries.size(); i++) {
                //                 System.out.printf("%d. %s\n", i, groceries.get(i-1));
                //             }
                //         }    

                //     // if user is new    
                //     } else {
                //         users.add(text[1]);
                //         glist.createNewFile();
                //     }    
                
                // break;
                
                case "users":
                break;
                }
        }

        
    }

    // Method to find a Shopper by name
    public static Shopper findShopperByName(HashSet<Shopper> users, String name) {
        for (Shopper shopper : users) {
            if (shopper.getName().equalsIgnoreCase(name)) {
                return shopper; // Return the found shopper
            }
        }
        return null; // Return null if not found
    }    
}








