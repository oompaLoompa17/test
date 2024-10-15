package Cookied;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class cookie {

    public cookie(){

    }

    public String whatCookie(File file, BufferedReader br) throws IOException{
        // Read from txt file
        String line;
        ArrayList<String> cookieList = new ArrayList<>();
        Random random = new Random();
        while ((line = br.readLine()) != null){
            cookieList.add(line);
            System.out.printf("Found %s\n", line);
        }
        // randomize cookie selection
        int randomIndex = random.nextInt(cookieList.size());
        String randomCookie = cookieList.get(randomIndex);
        return randomCookie;
    } 
}
