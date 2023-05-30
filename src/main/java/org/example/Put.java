package org.example;
import org.json.JSONObject;
import java.sql.*;

public class Put {
    private ConnectDatabase connectDatabase;

    public Put(ConnectDatabase connectDatabase){
        this.connectDatabase = connectDatabase;
    }
    public String putUsers(String userId, JSONObject requestBodyJson){
        String firstName = requestBodyJson.optString("first_name");
        String lastName = requestBodyJson.optString("last_name");
        String email = requestBodyJson.optString("email");
        String phoneNumber = requestBodyJson.optString("phone_number");
        String type = requestBodyJson.optString("type");
        PreparedStatement statement = null;
        String pesan = "Data Berhasil Diperbarui";

        String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, phone_number = ?, type = ? WHERE id=" + userId;
        try {
            statement = connectDatabase.getConnection().prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            statement.setString(5, type);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pesan;
    }

    public String putOrders(String userId, JSONObject requestBodyJson){
        int buyer = requestBodyJson.optInt("buyer");
        int note = requestBodyJson.optInt("note");
        int total = requestBodyJson.optInt("total");
        int discount = requestBodyJson.optInt("discount");
        String is_paid = requestBodyJson.optString("is_paid");
        PreparedStatement statement = null;
        int rowsAffected = 0;

        String query = "UPDATE products SET buyer = ?, note = ?, total = ?, discount = ?, is_paid = ? WHERE id=" + userId;
        try {
            statement = connectDatabase.getConnection().prepareStatement(query);
            statement.setInt(1, buyer);
            statement.setInt(2, note);
            statement.setInt(3, total);
            statement.setInt(4, discount);
            statement.setString(5, is_paid);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows inserted!";
    }

    public String putProducts(String userId, JSONObject requestBodyJson){
        int seller = requestBodyJson.optInt("seller");
        String title = requestBodyJson.optString("title");
        String description = requestBodyJson.optString("description");
        String price = requestBodyJson.optString("price");
        int stock = requestBodyJson.optInt("stock");
        PreparedStatement statement = null;
        String pesan = "Data Berhasil Diperbarui";

        String query = "UPDATE products SET seller = ?, title = ?, description = ?, price = ?, stock = ? WHERE id=" + userId;
        try {
            statement = connectDatabase.getConnection().prepareStatement(query);
            statement.setInt(1, seller);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setString(4, price);
            statement.setInt(5, stock);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pesan;
    }

}
