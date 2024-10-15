package Cookied;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class cserver {
    
    public static void main(String[] args) throws IOException {
        
        int port = Integer.parseInt(args[0]);

        ServerSocket server = new ServerSocket(port);

        while (true) { 
            // Wait for an incoming connection
            System.out.printf("Waiting for connection on port %d\n", port);

            Socket sock = server.accept();
            System.out.println("Got a new connection");
                
             // access the network stream
            InputStream is = sock.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            // create a byte array to hold the data
            byte[] buffer = new byte[1024];
            int bytesRead;

            //Stringbuilder to store the read data
            StringBuilder data = new StringBuilder();

            // Read data into the buffer and process
            while ((bytesRead = bis.read(buffer)) != -1) {
                // Convert bytes read to a String and append to data
                data.append(new String(buffer, 0, bytesRead));
            }

            // Access the stored data
            String readData = data.toString();
            System.out.println("Data read from the file:");
            System.out.println(readData);
            sock.close();
        }
    }
}
