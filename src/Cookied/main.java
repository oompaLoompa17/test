package Cookied;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

public class main {
        
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
       
    
             
        // Get the output stream
        OutputStream os = sock.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        cookie newcookie = new cookie(file);
        // write to server
        bw.write(cookiename.randomCookie(file));
        

        os.flush();
        writer.flush();
        bw.flush();
        sock.close();
    }
}
