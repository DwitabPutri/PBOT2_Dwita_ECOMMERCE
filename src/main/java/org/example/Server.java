package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.concurrent.Executors;
import java.io.InputStream;

public class Server {
    private Get get;
    private Put put;
    private Post post;
    private Delete delete;

    public Server(){
        ConnectDatabase connectDatabase = new ConnectDatabase();
        get = new Get(connectDatabase);
        delete = new Delete(connectDatabase);
        post = new Post(connectDatabase);
        put = new Put(connectDatabase);
    }

    public void startServer() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8074), 0);
        httpServer.createContext("/", new EcomHandler());
        httpServer.setExecutor(Executors.newSingleThreadExecutor());
        httpServer.start();
    }

    private class EcomHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String[] path = exchange.getRequestURI().getPath().split("/");
            String query = exchange.getRequestURI().getQuery();
            String response = "";
            if (method.equals("GET")) {
                if(path[1].equals("users")){
                    response = get.getUsersTertentu(path);
                }

            } else if (method.equals("DELETE")) {
                if(path[1].equals("users")){
                    response = delete.deleteData(Integer.parseInt(path[2]));
                }

            } else if (method.equals("POST")) {
                if(path[1].equals("users")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    response = post.postUsers(requestBodyJson);
                }

            } else if (method.equals("PUT")) {
                if(path[1].equals("users")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    response = put.putUsers(path[2], requestBodyJson);
                }

            }
            OutputStream outputStream = exchange.getResponseBody();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();
        }

        private JSONObject parseRequestBody(InputStream requestBody) throws IOException{
            byte[] requestBodyBytes = requestBody.readAllBytes();
            String requestBodyString = new String(requestBodyBytes);
            return new JSONObject(requestBodyString);
        }
    }
}
