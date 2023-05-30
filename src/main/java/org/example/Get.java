package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;

public class Get {
    private ConnectDatabase connectDatabase;

    public Get(ConnectDatabase connectDatabase){
        this.connectDatabase = connectDatabase;
    }
    public String getUsers(int usersId){
        JSONArray jsonArray = new JSONArray();
        String query = "";
        if(usersId == 0){
            query = "SELECT * FROM users";
        }
        else if(usersId == -1){
            query = "SELECT * FROM users WHERE type='Buyer'";
        }

        try (Connection connection = connectDatabase.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                JSONObject jsonUSer = new JSONObject();
                jsonUSer.put("id", resultSet.getInt("id"));
                jsonUSer.put("firstName", resultSet.getString("first_name"));
                jsonUSer.put("lastName", resultSet.getString("last_name"));
                jsonUSer.put("email", resultSet.getString("email"));
                jsonUSer.put("phoneNumber", resultSet.getString("phone_number"));
                jsonUSer.put("type", resultSet.getString("type"));
                jsonArray.put(jsonUSer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public String getUsersTertentu(String[] path){
        String response = "";
                if(path.length==2){
                    response = getUsers(0);
                }else if(path.length==3){
                    response=getUsers(Integer.parseInt(path[2]));
                }
        return response;
    }
}
