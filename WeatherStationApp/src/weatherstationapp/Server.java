package weatherstationapp;
import java.net.*;
import java.io.*;

public class Server {
    // initialise objects
    private Socket socket = null; 
    private ServerSocket server = null; 
    private DataInputStream in =  null; 
  
    // constructor for port 
    public Server(int port) 
    { 
        // starts server and awaits a connection 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
            System.out.println("Waiting for a client ..."); 
            socket = server.accept(); 
            System.out.println("Client accepted"); 
            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            // string for client message
            String line = ""; 
            // reads message from client until "Over" is sent 
            while (!line.equals("End")) 
            { 
                try
                { 
                    line = in.readUTF(); 
                    System.out.println(line);
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                } 
            } 
            System.out.println("Closing connection"); 
            // close connection 
            socket.close(); 
            in.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Server server = new Server(9090); 
    } 
} 
