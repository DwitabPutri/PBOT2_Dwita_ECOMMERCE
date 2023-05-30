package org.example;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
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
            String data= "";
            if (method.equals("GET")) {
                if(path[1].equals("users")){
                    data = get.getUsers(path);
                }
                else if(path[1].equals("product")){
                    data = get.getProducts(path);
                }
                else if(path[1].equals("orders")){
                    data = get.getOrders(path);
                }
                else if(path[1].equals("addresses")){
                    data = get.getAddresses(path);
                }
                else if(path[1].equals("order_details")){
                    data = get.getOrderDetails(path);
                }
                else if(path[1].equals("reviews")){
                    data = get.getReview(path);
                }

            } else if (method.equals("DELETE")) {
                if(path[1].equals("users")){
                    data = delete.deleteData(Integer.parseInt(path[2]));
                }
                else if(path[1].equals("orders")){
                    data = delete.deleteData(Integer.parseInt(path[2]));
                }
                else if(path[1].equals("product")){
                    data = delete.deleteData(Integer.parseInt(path[2]));
                }
                else if(path[1].equals("reviews")){
                    data = delete.deleteData(Integer.parseInt(path[2]));
                }
                else if(path[1].equals("order_details")){
                    data = delete.deleteData(Integer.parseInt(path[2]));
                }
                else if(path[1].equals("addresses")){
                    data = delete.deleteData(Integer.parseInt(path[2]));
                }

            } else if (method.equals("POST")) {
                if(path[1].equals("users")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = post.postUsers(requestBodyJson);
                }
                else if(path[1].equals("orders")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = post.postOrders(requestBodyJson);
                }
                else if(path[1].equals("reviews")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    //data = post.postReview(requestBodyJson);
                }
                else if(path[1].equals("order_details")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    //data = post.postOrderDetails(requestBodyJson);
                }
                else if(path[1].equals("products")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = post.postProducts(requestBodyJson);
                }
                else if(path[1].equals("addresses")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = post.postAddresses(requestBodyJson);
                }

            } else if (method.equals("PUT")) {
                if(path[1].equals("users")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = put.putUsers(path[2], requestBodyJson);
                }
                else if(path[1].equals("addresses")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = put.putAddresses(path[2], requestBodyJson);
                }
                else if(path[1].equals("product")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = put.putProducts(path[2], requestBodyJson);
                }
                else if(path[1].equals("orders")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = put.putOrders(path[2], requestBodyJson);
                }
                else if(path[1].equals("order_details")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = put.putOrderDetails(path[2], requestBodyJson);
                }
                else if(path[1].equals("reviews")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = put.putReview(path[2], requestBodyJson);
                }

            }
            OutputStream outputStream = exchange.getResponseBody();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, data.length());
            outputStream.write(data.getBytes());
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
