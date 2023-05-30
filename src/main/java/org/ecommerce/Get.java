package org.ecommerce;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;

public class Get {
    private ConnectDatabase connectDatabase;

    public Get(ConnectDatabase connectDatabase){
        this.connectDatabase = connectDatabase;
    }
    public String getUsersTertentu(int idUser){
        JSONArray jsonArray = new JSONArray();
        String query = "";
        if(idUser == 0){
            query = "SELECT * FROM users";
        }
        else if(idUser == -1){
            query = "SELECT * FROM users WHERE type='Buyer'";
        }
        else if(idUser == -2){
            query = "SELECT * FROM users WHERE type='Seller'";
        }

        try (Connection connection = connectDatabase.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                JSONObject jsonData = new JSONObject();
                jsonData.put("id", rs.getInt("id"));
                jsonData.put("firstName", rs.getString("first_name"));
                jsonData.put("lastName", rs.getString("last_name"));
                jsonData.put("email", rs.getString("email"));
                jsonData.put("phoneNumber", rs.getString("phone_number"));
                jsonData.put("type", rs.getString("type"));
                jsonArray.put(jsonData);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public String getUsers(String[] path){
        String data = "";
                if(path.length==2){
                    data = getUsersTertentu(0);
                }else if(path.length==3){
                    data=getUsersTertentu(Integer.parseInt(path[2]));
                }
        return data;
    }


    public String getProducts(String[] path){
        String data = "";
        if(path.length == 2){
            data = getProductsTertentu(0);
        }else{
            data= getProductsTertentu(Integer.parseInt(path[2]));
        }
        return data;
    }

    public String getProductsTertentu(int idProduk){
        JSONArray jsonArray = new JSONArray();
        String query = "";
        switch (idProduk){
            case 0 :
                query = "SELECT * FROM products";
                break;
            default:
                query = "SELECT * FROM products WHERE id=" + idProduk;
                break;
        }

        try (Connection connection = connectDatabase.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                JSONObject jsonData = new JSONObject();
                jsonData.put("id", rs.getInt("id"));
                jsonData.put("seller", rs.getInt("seller"));
                jsonData.put("title", rs.getString("title"));
                jsonData.put("description", rs.getString("description"));
                jsonData.put("price", rs.getString("price"));
                jsonData.put("stock", rs.getInt("stock"));
                jsonArray.put(jsonData);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public String getOrders(String[] path){
        String data = "";
        if(path.length == 2){
            data = getOrdersTertentu(0);
        }else{
            data = getOrdersTertentu(Integer.parseInt(path[2]));
        }
        return data;
    }

    public String getOrdersTertentu(int idBuyer){
        JSONArray jsonArray = new JSONArray();
        String query = "SELECT * FROM orders WHERE buyer=" + idBuyer;
        try (Connection connection = connectDatabase.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                JSONObject jsonData = new JSONObject();
                jsonData.put("id", rs.getInt("id"));
                jsonData.put("buyer", rs.getInt("buyer"));
                jsonData.put("note", rs.getInt("note"));
                jsonData.put("total", rs.getInt("total"));
                jsonData.put("discount", rs.getInt("discount"));
                jsonData.put("is_paid", rs.getString("is_paid"));
                jsonArray.put(jsonData);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public String getAddresses(String[] path){
        String data = "";
        if(path.length == 2){
            data = getAdressesTertentu(0);
        }else{
            data = getAdressesTertentu(Integer.parseInt(path[2]));
        }
        return data;
    }

    public String getAdressesTertentu(int idUser){
        JSONArray jsonArray = new JSONArray();
        String query = "SELECT * FROM addresses WHERE users=" + idUser;
        try (Connection connection = connectDatabase.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                JSONObject jsonData = new JSONObject();
                jsonData.put("users", rs.getInt("users"));
                jsonData.put("type", rs.getString("type"));
                jsonData.put("line1", rs.getString("line1"));
                jsonData.put("line2", rs.getString("line2"));
                jsonData.put("city", rs.getString("city"));
                jsonData.put("province", rs.getString("province"));
                jsonData.put("postcode", rs.getString("postcode"));
                jsonArray.put(jsonData);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public String getOrderDetails(String[] path){
        String data = "";
        if(path.length == 2){
            data = getOrderDetailsTertentu(0);
        }else{
            data = getOrderDetailsTertentu(Integer.parseInt(path[2]));
        }
        return data;
    }

    public String getReviewTertentu(int idReview){
        JSONArray jsonArray = new JSONArray();
        String query = "SELECT * FROM reviews WHERE order=" + idReview;
        try (Connection connection = connectDatabase.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                JSONObject jsonData = new JSONObject();
                jsonData.put("order", rs.getInt("order"));
                jsonData.put("star", rs.getInt("star"));
                jsonData.put("description", rs.getString("description"));
                jsonArray.put(jsonData);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public String getReview(String[] path){
        String data = "";
        if(path.length == 2){
            data = getReviewTertentu(0);
        }else{
            data = getReviewTertentu(Integer.parseInt(path[2]));
        }
        return data;
    }

    public String getOrderDetailsTertentu(int idOrder){
        JSONArray jsonArray = new JSONArray();
        String query = "SELECT * FROM order_details WHERE buyer=" + idOrder;
        try (Connection connection = connectDatabase.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                JSONObject jsonData = new JSONObject();
                jsonData.put("product", rs.getInt("product"));
                jsonData.put("quantity", rs.getInt("quantity"));
                jsonData.put("price", rs.getInt("price"));
                jsonArray.put(jsonData);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

}
