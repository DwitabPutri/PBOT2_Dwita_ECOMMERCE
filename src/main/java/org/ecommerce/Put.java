package org.ecommerce;
import org.json.JSONObject;
import java.sql.*;

public class Put {
    private ConnectDatabase connectDatabase;

    public Put(ConnectDatabase connectDatabase){
        this.connectDatabase = connectDatabase;
    }
    public String putUsers(String id, JSONObject requestBodyJson){
        String firstName = requestBodyJson.optString("first_name");
        String lastName = requestBodyJson.optString("last_name");
        String email = requestBodyJson.optString("email");
        String phoneNumber = requestBodyJson.optString("phone_number");
        String type = requestBodyJson.optString("type");
        PreparedStatement statement = null;
        String pesan = "Data Berhasil Diperbarui";

        String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, phone_number = ?, type = ? WHERE id=" + id;
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

    public String putOrders(String id, JSONObject requestBodyJson){
        int buyer = requestBodyJson.optInt("buyer");
        int note = requestBodyJson.optInt("note");
        int total = requestBodyJson.optInt("total");
        int discount = requestBodyJson.optInt("discount");
        String is_paid = requestBodyJson.optString("is_paid");
        PreparedStatement statement = null;
        String pesan = "Data Berhasil Diperbarui";

        String query = "UPDATE products SET buyer = ?, note = ?, total = ?, discount = ?, is_paid = ? WHERE id=" + id;
        try {
            statement = connectDatabase.getConnection().prepareStatement(query);
            statement.setInt(1, buyer);
            statement.setInt(2, note);
            statement.setInt(3, total);
            statement.setInt(4, discount);
            statement.setString(5, is_paid);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pesan;
    }

    public String putProducts(String id, JSONObject requestBodyJson){
        int seller = requestBodyJson.optInt("seller");
        String title = requestBodyJson.optString("title");
        String description = requestBodyJson.optString("description");
        String price = requestBodyJson.optString("price");
        int stock = requestBodyJson.optInt("stock");
        PreparedStatement statement = null;
        String pesan = "Data Berhasil Diperbarui";

        String query = "UPDATE products SET seller = ?, title = ?, description = ?, price = ?, stock = ? WHERE id=" + id;
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

    public String putReview(String id, JSONObject requestBodyJson){
        int order = requestBodyJson.optInt("order");
        int star = requestBodyJson.optInt("star");
        String description = requestBodyJson.optString("description");
        PreparedStatement statement = null;
        String pesan = "Data Berhasil Diperbarui";

        String query = "UPDATE reviews SET order = ?, star = ?, description = ? WHERE id=" + id;
        try {
            statement = connectDatabase.getConnection().prepareStatement(query);
            statement.setInt(1, order);
            statement.setInt(2, star);
            statement.setString(3, description);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pesan;
    }

    public String putOrderDetails(String id, JSONObject requestBodyJson){
        int product = requestBodyJson.optInt("product");
        int quantity= requestBodyJson.optInt("quantity");
        int price = requestBodyJson.optInt("price");
        PreparedStatement statement = null;
        String pesan = "Data Berhasil Diperbarui";

        String query = "UPDATE order_details SET product = ?, quantity = ?, price = ? WHERE id=" + id;
        try {
            statement = connectDatabase.getConnection().prepareStatement(query);
            statement.setInt(1, product);
            statement.setInt(2, quantity);
            statement.setInt(3, price);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pesan;
    }

    public String putAddresses(String id, JSONObject requestBodyJson){
        int users = requestBodyJson.optInt("users");
        String type = requestBodyJson.optString("type");
        String line1 = requestBodyJson.optString("line1");
        String line2 = requestBodyJson.optString("line2");
        String city = requestBodyJson.optString("city");
        String province =requestBodyJson.optString("province");
        String postcode = requestBodyJson.optString("postcode");
        PreparedStatement statement = null;
        String pesan = "Data Berhasil Diperbarui";

        String query = "UPDATE addresses SET users = ?, type = ?, line1 = ?, line2 = ?, city = ?, province = ?, postcode = ? WHERE id=" + id;
        try {
            statement = connectDatabase.getConnection().prepareStatement(query);
            statement.setInt(1, users);
            statement.setString(2, type);
            statement.setString(3, line1);
            statement.setString(4, line2);
            statement.setString(5, city);
            statement.setString(6, province);
            statement.setString(7, postcode);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pesan;
    }

}
