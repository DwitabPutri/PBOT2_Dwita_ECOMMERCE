package org.example;

import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Post {
    private ConnectDatabase connectDatabase;

    public Post(ConnectDatabase connectDatabase) {
        this.connectDatabase = connectDatabase;
    }

    public String postUsers(JSONObject requestBodyJson) {
        String firstName = requestBodyJson.optString("first_name");
        String lastName = requestBodyJson.optString("last_name");
        String email = requestBodyJson.optString("email");
        String phoneNumber = requestBodyJson.optString("phone_number");
        String type = requestBodyJson.optString("type");
        PreparedStatement statement = null;
        int rowsAffected = 0;

        String query = "INSERT INTO users(first_name, last_name, email, phone_number, type) VALUES(?,?,?,?,?)";
        try {
            statement = connectDatabase.getConnection().prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            statement.setString(5, type);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected + " rows inserted!";
    }
}
