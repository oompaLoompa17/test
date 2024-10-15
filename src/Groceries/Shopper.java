package Groceries;

import java.util.ArrayList;

public class Shopper {

public ArrayList<String> groceries;
public String name;
public int itemNo;

    public Shopper(String name){
        this.name = name;
        this.groceries = new ArrayList<>();
    }

    public void list (){
        if (this.groceries.isEmpty()){ //OR if idx == 0 ?
            System.out.println("Your cart is empty");
        }
        else {
            for (int i=1; i <= this.groceries.size(); i++) {
                System.out.printf("%d. %s\n", i, this.groceries.get(i-1));
            }
        }
    }

    public void add(String s){  
        // check if item to add has already been added
        if (this.groceries.contains(s)){
            System.out.printf("You already have %s in your cart!\n", s);
        }
        else {
            this.itemNo++;
            this.groceries.add(s);
            System.out.printf("%s added to cart.\n", s);
        }
    }

    public void delete(int idx){
        if (idx > this.itemNo) {
            System.out.println("Incorrect item index");
        }
        else {
            System.out.printf("%s removed from cart.\n", this.groceries.remove(idx-1));
            this.groceries.remove(idx-1);
            this.itemNo--;
        }
    }

    public ArrayList<String> getGroceries() {return groceries;}
    public void setGroceries(ArrayList<String> groceries) {this.groceries = groceries;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getItemNo() {return itemNo;}
    public void setItemNo(int itemNo) {this.itemNo = itemNo;}

    
}

