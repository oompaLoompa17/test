package Cookied;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    
    public static void main(String[] args) throws IOException {
        
        int port = Integer.parseInt(args[0]);
        ServerSocket server = new ServerSocket(port);
        File file = new File(args[1]);

        while (true) { 
            // Wait for an incoming connection
            System.out.printf("Waiting for connection on port %d\n", port);

            Socket sock = server.accept();
            System.out.println("Got a new connection");
            
            // create outputstream
            OutputStream os = sock.getOutputStream();
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

             // access the network stream
            InputStream is = sock.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);          
            
            cookie newcookie = new cookie();
            // write to server
            bw.write(newcookie.whatCookie(file));
            
            os.close();
            bw.close();
            sock.close();
        }
    }
}
