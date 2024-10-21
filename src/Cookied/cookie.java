package Cookied;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class cookie {

       public String whatCookie(File file) throws IOException{
        Reader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);

        // Read from txt file
        String line;
        ArrayList<String> cookieList = new ArrayList<>();
        while ((line = br.readLine()) != null){
            cookieList.add(line);
            System.out.printf("Found %s\n", line);
        }
        br.close();
        reader.close();
        
        // randomize cookie selection
        Random random = new Random();
        int randomIndex = random.nextInt(cookieList.size());
        String randomCookie = cookieList.get(randomIndex);
        return randomCookie;
    } 
}
