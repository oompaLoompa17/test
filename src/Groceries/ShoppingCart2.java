package Groceries;
import java.io.*;
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

            if (text[0].equalsIgnoreCase("login")){
                username = text[1];
                for (Shopper user : users){                         // WHY THIS LINE DONT WORK
                    System.out.println("0");
                    if (username.equalsIgnoreCase(user.getName())) {
                        System.out.printf("%s is already logged in.\n", username);
                    } else {
                        // Create a new Shopper
                        Shopper shopper = new Shopper(username);
                        System.out.println("1");
                        proxy = shopper;
                        System.out.println("2");
                        shopper.list();
                        System.out.println("3");
                        users.add(shopper);  // Add the new shopper to the HashSet
                        System.out.println("4");
                        // Optionally create the user's grocery list file
                        File glist = new File(dirName + "/" + username + ".txt");
                        System.out.println("5");
                        glist.createNewFile();
                        System.out.println("6");
                        System.out.printf("Welcome, %s! You have been logged in.\n", username);
                    }
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

                case "save":
                    // output stream
                    FileWriter fw = new FileWriter(dirName);
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (String s : proxy.groceries){
                        bw.write(s);
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








