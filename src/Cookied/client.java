package Cookied;

import java.io.*;
import java.net.*;

public class client {
        
    public static void main(String[] args) throws IOException {
        
        int port = Integer.parseInt(args[0].split(":")[1]);
        String server = args[0].split(":")[0];

        System.out.println("Connecting to the server");
        Socket sock = new Socket(server, port);
        System.out.println("Connected!");
       

        InputStream is = sock.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        // DataInputStream dis = new DataInputStream(bis);

        OutputStream os = sock.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        // create a byte array to hold the data
        byte[] buffer = new byte[1024];
        int bytesRead;

        //Stringbuilder to store the read data
        StringBuilder data = new StringBuilder();

        try {
            // Read data into the buffer and process
            while ((bytesRead = bis.read(buffer)) != -1) {
                // Convert bytes read to a String and append to data
                data.append(new String(buffer, 0, bytesRead));
            }           
        } catch (SocketException e){
            System.err.println("SocketException: " + e.getMessage());
            e.printStackTrace();
        }

        // Access the stored data
        String readData = data.toString();
        System.out.println("Data read from the file:");
        System.out.println(readData);
        

        os.flush();
        writer.flush();
        bw.flush();
        sock.close();
    }
}
