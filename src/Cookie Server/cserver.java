package Cookie;

import java.io.*;
import java.net.ServerSocket;


public class cserver {
    
    public static void main(String[] args) throws IOException {
        
        int port = Integer.parseInt(args[2]);

        ServerSocket server = new ServerSocket(port);

        while (true) { 
            // Wait for an incoming connection
            System.out.printf("Waiting for connection on port %d\n", port);

            Socket sock = server.accept();
            System.out.println("Got a new connection");
                
            // Open a OutputStream
            OutputStream os = sock.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            // Open the file for reading
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);

            // Get the file information
            String fileName = file.getName();
            long fileSize = file.length();

            // Write the file metadata
            dos.writeUTF(fileName);
            dos.writeLong(fileSize); 
        }
    }
}
