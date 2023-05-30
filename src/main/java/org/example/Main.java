package org.example;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
    try{
        server.startServer();
    }
    catch (IOException e){
        e.printStackTrace();
        }
    }
}