package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
public class Delete {
    private ConnectDatabase connectDatabase;

    public Delete(ConnectDatabase connectDatabase){
        this.connectDatabase = connectDatabase;
    }
    public String deleteData(int userId){
        PreparedStatement statement = null;
        int rowsAffected = 0;
        try {
            String query = "DELETE FROM users WHERE id=" + userId;
            statement = this.connectDatabase.getConnection().prepareStatement(query);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rowsAffected + " rows deleted!";
    }

}
