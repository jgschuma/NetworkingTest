
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jacob Games
 */
public class NetworkAppExample {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 10430;
        
        ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(host));
        System.out.println("Server started.");
        Socket client = new Socket(host, port);
        System.out.println("Connecting to server...");
        Socket connection = server.accept();
        System.out.println("Connection established.");
        
        ObjectOutputStream clientOut = new ObjectOutputStream(client.getOutputStream());
        ObjectOutputStream serverOut = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream clientIn = new ObjectInputStream(client.getInputStream());
        ObjectInputStream serverIn = new ObjectInputStream(connection.getInputStream());
        System.out.println("Communication is ready.");
        
        String messageOut = "Hello World";
        clientOut.writeObject(messageOut);
        clientOut.flush();
        System.out.println("Message sent to server: " + messageOut);
        
        String messageIn = (String) serverIn.readObject();
        System.out.println("Message recieved from client: " + messageIn);
        
        clientOut.close();
        serverOut.close();
        System.out.println("Connections closed.");
        server.close();
        System.out.println("Server terminated.");
    }
}
