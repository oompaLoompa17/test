package Groceries;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

// add, remove and list the contents of a shopping cart
public class ShoppingCart2 {

    public static void main(String[] args) throws IOException, FileNotFoundException {
        
        // check dir name to write file into
        String dirName;
        if (args.length > 0){dirName = args[0];
        } else{dirName = "db";}

        // create new dir if name is not taken alr
        Dir dir = new Dir();
        dir.newDir(dirName);     
        Path filePath = null;

        Console cons = System.console();

        // create a hashset to store shoppers
        HashSet<Shopper> users = new HashSet<>();
        String username;
        Shopper proxy = null;
        System.out.println("Welcome to your shopping cart!");

        while (true) {

            // store command line args in String array
            String input = cons.readLine("> ").trim();
            String[] text = input.split(" "); 
            for (int i=0; i < text.length; i++){
                text[i] = text[i].replace(",","");
            }

            // if (text[0].equalsIgnoreCase("login")){
            //     username = text[1];
            //     for (Shopper user : users){                                      // WHY THIS LINE DONT WORK: block of code below will not execute because 
            //         System.out.println("0");                                     // ArrayList<Shopper> users is empty, i.e. it will never initialize a new shopper
            //         if (username.equalsIgnoreCase(user.getName())) {
            //             System.out.printf("%s is already logged in.\n", username);
            //         } else {
            //             // Create a new Shopper
            //             Shopper shopper = new Shopper(username);
            //             proxy = shopper;
            //             shopper.list();
            //             users.add(shopper);  // Add the new shopper to the HashSet
            //             // Optionally create the user's grocery list file
            //             File glist = new File(dirName + "/" + username + ".txt");
            //             glist.createNewFile();
            //             System.out.printf("Welcome, %s! You have been logged in.\n", username);
            //         }
            //     }
            // }

            if (text[0].equalsIgnoreCase("login")) {
                username = text[1];
                boolean userFound = false; // Flag to check if user is already logged in    
            
                // Check if the user is already in the HashSet                              // CURRENT PROBLEM: UNABLE TO SHIFT FROM USER 1 TO USER 2,
                for (Shopper user : users) {                                                // 2ND SAVE ON USER 2 ENDS UP SAVING ON USER 1
                    System.out.println("Checking user: " + user.getName());
                    if (username.equalsIgnoreCase(user.getName())) {
                        userFound = true; // User already exists
                        System.out.printf("%s is already logged in.\n", username);
                        break; // Exit the loop
                    }
                }
            
                // If user is not found in the existing users, create a new Shopper
                if (!userFound) {
                    System.out.println("Creating new Shopper...");
                    Shopper shopper = new Shopper(username);
                    proxy = shopper; // Store reference to the new shopper
                    shopper.list(); // Call list on the new shopper
                    users.add(shopper);  // Add the new shopper to the HashSet
                    
                    // File glist = new File(dirName + "/" + username + ".txt");        //PROBLEM: Using "/" in Paths.get() is unnecessary
                    // glist.createNewFile();
                    // filePath = Paths.get(dirName, "/", username, ".txt");

                    filePath = Paths.get(dirName, username + ".txt"); // Correctly build the file path
                    File glist = filePath.toFile(); // Convert Path to File
                    glist.createNewFile(); // Create the file on disk
                    System.out.printf("Welcome, %s! You have been logged in.\n", username);
                }
            }
            
            
            switch (text[0].toLowerCase()) {

                case "list":
                    proxy.list();
                    break;

                case "add":            
                    for (String s: text){
                        // omit the "add" from the command line check
                        if (s.equals("add")) {
                            continue;
                        }
                        proxy.add(s);
                    }
                    break;

                case "delete":
                    int itemdx = Integer.parseInt(text[1]);
                    proxy.delete(itemdx);
                break;

                // case "save":
                //     // output stream
                //     FileWriter fw = new FileWriter(filePath.toFile());
                //     BufferedWriter bw = new BufferedWriter(fw);
                //     for (String s : proxy.groceries){
                //         bw.write(s);
                //     }
                // break;
                
                case "save":
                    if (filePath != null) { // Check if filePath is initialized
                        try (FileWriter fw = new FileWriter(filePath.toFile());
                             BufferedWriter bw = new BufferedWriter(fw)) {
                            for (String s : proxy.groceries) {
                                bw.write(s);
                                bw.newLine();
                            }
                            System.out.println("Grocery list saved successfully.");
                        } catch (IOException e) {
                            System.err.println("Error saving grocery list: " + e.getMessage());
                        }
                    } else {
                        System.out.println("File path has not been initialized.");
                    }
                    break;

                case "users":
                    System.out.println("The following users are registered: " + users);
                break;
                }
        }
    }

    //Method to find a Shopper by name
    public static Shopper findShopperByName(HashSet<Shopper> users, String name) {
        for (Shopper shopper : users) {
            if (shopper.getName().equalsIgnoreCase(name)) {
                return shopper; // Return the found shopper
            }
        }
        return null; // Return null if not found
    }    
}








