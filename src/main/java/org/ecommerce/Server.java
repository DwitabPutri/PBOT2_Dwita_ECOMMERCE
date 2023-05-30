package org.ecommerce;
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
            String data= "";
            //ketika method yang dipilih sesuai dengann "Get" maka akan dijalankan kondisi get
            if (method.equals("GET")) {
                //kondisi jika path 1 = users, maka akan dijalankan getUsers
                if(path[1].equals("users")){
                    data = get.getUsers(path);
                }
                //kondisi jika path 1 = products, maka akan dijalankan getproducts
                else if(path[1].equals("products")){
                    data = get.getProducts(path);
                }
                //kondisi jika path 1 = orders, maka akan dijalankan getorders
                else if(path[1].equals("orders")){
                    data = get.getOrders(path);
                }
                //kondisi jika path 1 = addresses, maka akan dijalankan getaddresses
                else if(path[1].equals("addresses")){
                    data = get.getAddresses(path);
                }
                //kondisi jika path 1 = order_details, maka akan dijalankan getorder_details
                else if(path[1].equals("order_details")){
                    data = get.getOrderDetails(path);
                }
                //kondisi jika path 1 = reviews, maka akan dijalankan getReview
                else if(path[1].equals("reviews")){
                    data = get.getReview(path);
                }

            //kondisi kedua jika method sama dengan delete
            } else if (method.equals("DELETE")) {
                //nested if == kondisi ketika path = users
                if(path[1].equals("users")){
                    data = delete.deleteData(Integer.parseInt(path[2]));
                }
                //kondisi ketika path 1 = orders
                else if(path[1].equals("orders")){
                    data = delete.deleteData(Integer.parseInt(path[2]));
                }
                //kondisi ketika path 1 = products
                else if(path[1].equals("products")){
                    data = delete.deleteData(Integer.parseInt(path[2]));
                }
                //kondisi ketika path 1 = reviews
                else if(path[1].equals("reviews")){
                    data = delete.deleteData(Integer.parseInt(path[2]));
                }
                //kondisi ketika path 1 = order_details
                else if(path[1].equals("order_details")){
                    data = delete.deleteData(Integer.parseInt(path[2]));
                }
                //kondisi ketika path 1 = addresses
                else if(path[1].equals("addresses")){
                    data = delete.deleteData(Integer.parseInt(path[2]));
                }

                //kondisi ketiga jika method sama dengan post
            } else if (method.equals("POST")) {
                //nested if == kondisi ketika path = users
                if(path[1].equals("users")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = post.postUsers(requestBodyJson);
                }
                //kondisi ketika path 1 = orders
                else if(path[1].equals("orders")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = post.postOrders(requestBodyJson);
                }
                //kondisi ketika path 1 = reviews
                else if(path[1].equals("reviews")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = post.postReview(requestBodyJson);
                }
                //kondisi ketika path 1 = order_details
                else if(path[1].equals("order_details")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = post.postOrderDetails(requestBodyJson);
                }
                //kondisi ketika path 1 = products
                else if(path[1].equals("products")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = post.postProducts(requestBodyJson);
                }
                //kondisi ketika path 1 = addresses
                else if(path[1].equals("addresses")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = post.postAddresses(requestBodyJson);
                }

                //kondisi keempat jika method sama dengan put
            } else if (method.equals("PUT")) {
                //nested if == kondisi ketika path = users
                if(path[1].equals("users")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = put.putUsers(path[2], requestBodyJson);
                }
                //kondisi ketika path 1 = addresses
                else if(path[1].equals("addresses")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = put.putAddresses(path[2], requestBodyJson);
                }
                //kondisi ketika path 1 = product
                else if(path[1].equals("product")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = put.putProducts(path[2], requestBodyJson);
                }
                //kondisi ketika path 1 = orders
                else if(path[1].equals("orders")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = put.putOrders(path[2], requestBodyJson);
                }
                //kondisi ketika path 1 = order_details
                else if(path[1].equals("order_details")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    data = put.putOrderDetails(path[2], requestBodyJson);
                }
                //kondisi ketika path 1 = reviews
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
