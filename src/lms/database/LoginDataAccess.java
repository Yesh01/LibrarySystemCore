package lms.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDataAccess {

    public String doLogin(Connection connection, String userName, String password) throws SQLException{

        String query = "SELECT * FROM login WHERE user_name = ? AND PASSWORD = ?"; // --> Querry in User & Admin to Log-in

        // ---> Connection for Private Data to Login
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userName);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                
                if(rs.next()) {
                    return rs.getString("user_type"); // ---> Determining the User Type who Logged in
                }
            }
        }

        return null; // --->

    }
}
