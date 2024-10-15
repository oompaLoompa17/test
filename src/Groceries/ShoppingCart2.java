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

        File directory = new File(dirName);
        if (!directory.exists()){
            boolean dirExists = directory.mkdir();
            if (dirExists){
                System.out.println("Directory created " + dirName);
            } else{
                System.out.println("Failed to create directory " + dirName);
            }
        } else{
            System.out.println("Using directory: " + dirName);
        }


        // output stream
        FileWriter fw = new FileWriter(dirName);
        BufferedWriter bw = new BufferedWriter(fw);
        

        Console cons = System.console();

        // create the list
        ArrayList<String> groceries = new ArrayList<String>();
        HashSet<String> users = new HashSet<>();

        // index for the items on the list
        int idx = 0;

        System.out.println("Welcome to your shopping cart!");

        while (true) {

            String input = cons.readLine("> ").trim();

            // store command sentence in array
            String[] text = input.split(" "); 
            
            // check array for strings that contain "," and replace with ""
            for (int i=0; i < text.length; i++){
                text[i] = text[i].replace(",","");
            }

            switch (text[0].toLowerCase()) {

                case "list":
                    if (groceries.isEmpty()){ //OR if idx == 0 ?
                        System.out.println("Your cart is empty");
                    }
                    else {
                        for (int i=1; i <= groceries.size(); i++) {
                            System.out.printf("%d. %s\n", i, groceries.get(i-1));
                        }
                    }
                    break;

                case "add":            
                    for (String s: text){
                        // omit the "add" from the command line check
                        if (s.equals("add")) {
                            continue;
                        }
                        // check if item to add has already been added
                        else if (groceries.contains(s)){
                            System.out.printf("You already have %s in your cart!\n", s);
                        }
                        else {
                            idx++;
                            groceries.add(s);
                            System.out.printf("%s added to cart.\n", s);
                        }
                    }
                    break;

                case "delete":
                    int itemdx = Integer.parseInt(text[1]);
                    if (itemdx > idx) {
                        System.out.println("Incorrect item index");
                    }
                    else {
                        System.out.printf("%s removed from cart.\n", groceries.remove(itemdx-1));
                        groceries.remove(itemdx-1);
                        idx--;
                    }
                    break;

                case "save":
                    dirName
                break;

                case "login":
                    String user = text[1];

                    File glist = new File("test/src/" + text[1] + ".txt");
                    BufferedInputStream br = new BufferedInputStream(new FileInputStream(glist));

                    // user exists
                    if (users.contains(user)){
                        // but list is empty
                        if (groceries.isEmpty()){
                            System.out.printf("%s, your cart is empty.", user);
                        // and list has items
                        } else{
                            System.out.printf("%s, your cart contains the following items\n", user);
                            for (int i=1; i <= groceries.size(); i++) {
                                System.out.printf("%d. %s\n", i, groceries.get(i-1));
                            }
                        }    

                    // if user is new    
                    } else {
                        users.add(text[1]);
                        glist.createNewFile();
                    }    
                
                break;
                
                case "users":
                break;
                }
        }
    }
}








