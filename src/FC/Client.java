package FC;

import java.io.*;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) throws IOException {
        
        String[] text = args[0].split(":");
        String host = text[0];
        int port = Integer.parseInt(text[1]);
        
        Console cons = System.console();

        Socket socket = new Socket(host, port);
        System.out.println("Connected to server on port: " + port);

        try (OutputStream os = socket.getOutputStream();
             BufferedOutputStream bos = new BufferedOutputStream(os);
             DataOutputStream dos = new DataOutputStream(bos);
             InputStream is = socket.getInputStream();
             BufferedInputStream bis = new BufferedInputStream(is);
             DataInputStream dis = new DataInputStream(bis)) {
             
            while (true){
            System.out.println("Send your request to server in the form of: get-cookie/close"); 
            String request = cons.readLine();
            dos.writeUTF(request);
            dos.flush(); // Make sure the data is sent

                if (request.equalsIgnoreCase("get-cookie")){
                // Read response from server
                String cookieResponse = dis.readUTF();
                System.out.println("Received from server: " + cookieResponse); // Debug output
                } else if(request.equalsIgnoreCase("close")){
                    break;
                }   
            }
        } // All streams will be closed here    
    }
}
