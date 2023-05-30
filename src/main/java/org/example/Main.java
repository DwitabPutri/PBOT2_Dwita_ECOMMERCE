package org.example;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
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