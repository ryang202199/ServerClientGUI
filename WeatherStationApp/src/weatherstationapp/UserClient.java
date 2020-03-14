package weatherstationapp;
    import java.net.*;
    import java.io.*;

public class UserClient {
        // initialise objects
    private Socket socket = null; 
    private DataInputStream  input = null; 
    private DataOutputStream out = null; 
  
    // constructor for ip address and port 
    public UserClient(String address, int port) 
    { 
        // establish connection
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
            // takes input from terminal
            input  = new DataInputStream(System.in); 
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
        // string to read message from input 
        String text = "";
        // loop until "Over" is input 
        while (!text.equals("End")) 
        { 
            try
            { 
                text = input.readLine();
                out.writeUTF(text); 
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
        } 
        // close connection 
        try
        { 
            input.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        UserClient client = new UserClient("localhost", 9090); 
    } 
}
