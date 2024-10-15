import java.io.Console;
import java.util.ArrayList;

// add, remove and list the contents of a shopping cart
public class ShoppingCart {
    public static void main(String[] args) {
        
        Console cons = System.console();

        // create the list
        ArrayList<String> groceries = new ArrayList<String>();

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
                
                }
        }
    }
}








