package Cookie;


import java.io.*;
import java.net.*;

public class cmain {
    
    public static void main(String[] args) throws IOException {
        
        int port = Integer.parseInt(args[1]);
        String server = args[0];

        // create a file object to pass file name into
        File file = new File(args[2]);
        String fileName = file.getName();
        Long fileLength = file.length();

        System.out.println("Connecting to the server");
        Socket sock = new Socket(server, port);
        System.out.println("Connected!");

        // Get the output stream
        OutputStream os = sock.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);
        
        // Get the input stream
        InputStream is = sock.getInputStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);


        // Read the result from the server
        String fromServer = br.readLine();
        System.out.printf(">>> SERVER: %s\n", fromServer);

        br.flush();
        os.flush();
    }
}
