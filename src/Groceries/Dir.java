package Groceries;

import java.io.File;

public class Dir {

    // creates new directory for list to be saved in
    public void newDir (String dirName){
        File directory = new File(dirName);
        if (!directory.exists()){
            boolean dirExists = directory.mkdir();
            System.out.println("Directory created " + dirName);          
        } else{
            System.out.println("Using directory: " + dirName);
        }
    }    

}
