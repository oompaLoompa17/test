package FC;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    public static void main(String[] args) throws IOException {
        
        int port = Integer.parseInt(args[0]);
        String file = args[1];

        ServerSocket serverSocket = new ServerSocket(port); // opens a port to accept connection
        System.out.println("Server started on port: " + port);
        
        try (Socket socket = serverSocket.accept()) { // Accept connection
            System.out.println("Client connected.");

            try (InputStream is = socket.getInputStream();
                 BufferedInputStream bis = new BufferedInputStream(is);
                 DataInputStream dis = new DataInputStream(bis);
                 OutputStream os = socket.getOutputStream();
                 BufferedOutputStream bos = new BufferedOutputStream(os);
                 DataOutputStream dos = new DataOutputStream(bos)) {
                
                while (true){
                    System.out.println("Waiting for message from client...");
                    String line = dis.readUTF(); 
                    System.out.println("Received message: " + line); // Debug output

                    // Check the content of line
                    if (line != null && line.equals("get cookie!")) { 
                        System.out.println("Processing request for cookie...");
                        Cookie cookie = new Cookie(file);
                        String randomCookie = cookie.getCookie();
                        System.out.println("cookie-text: " + randomCookie);
                        dos.writeUTF(randomCookie);
                        dos.flush(); // Ensure the data is sent
                    } else if (line != null && line.equals("close")){
                     break;   
                    } else {
                        System.out.println("Unknown request: " + line);
                    }
                }
            } // All streams will be closed here
    
        } catch (IOException e) {
            System.err.println("Socket not connected: " + e.getMessage());
        }
    }
}
