package org.example;
import java.sql.*;
public class Delete {
    private ConnectDatabase connectDatabase;

    public Delete(ConnectDatabase connectDatabase){
        this.connectDatabase = connectDatabase;
    }
    public String deleteData(int idHapus){
        PreparedStatement statement = null;
        String pesan = "Data Berhasil Dihapus";
        try {
            String query = "DELETE FROM users WHERE id=" + idHapus;
            statement = this.connectDatabase.getConnection().prepareStatement(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return pesan;
    }

}
