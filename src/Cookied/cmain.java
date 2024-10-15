package Cookied;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

public class cmain {
        
    public static void main(String[] args) throws IOException {
        
        int port = Integer.parseInt(args[0]);
        String server = "localhost";

        // create a file object to pass file name into
        File file = new File(args[1]);
        String fileName = file.getName();
        Long fileLength = file.length();


        System.out.println("Connecting to the server");
        Socket sock = new Socket(server, port);
        System.out.println("Connected!");
       
        // Get the input stream
        // InputStream is = sock.getInputStream();
        // Reader reader = new InputStreamReader(is);
        Reader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);

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
             
        // Get the output stream
        OutputStream os = sock.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        // write to server
        bw.write(randomCookie);
        

        os.flush();
        writer.flush();
        bw.flush();
        sock.close();
    }
}
