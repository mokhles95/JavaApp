/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Implementation;

import Tools.DataSource;
import Entities.FosUser;
import Entities.Offer;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Emir Lanouar
 */
public class ServiceUser {

    private static ServiceUser userServiceInstance;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    static Connection cnx = DataSource.dbConnexion();
    private static final Logger logger = Logger.getLogger(ServiceUser.class.getName());

    public static ServiceUser getInstance() {
        if (userServiceInstance == null) {
            userServiceInstance = new ServiceUser();
        }
        return userServiceInstance;
    }

      public User getUserByUsername(String username) {
        try {
            String req = "select * from fos_user where username=?";
            User u = null;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                u = User.createUser(rs);
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public User login(String username, String password) throws SQLException {
        String query = "SELECT * FROM fos_user WHERE email = ?";
        PreparedStatement preparedStmt = cnx.prepareStatement(query);
        preparedStmt.setString(1, username);

        ResultSet rs = preparedStmt.executeQuery();
        while (rs.next()) {

            User user_found = new User();

            user_found.setId(rs.getInt("id"));
            user_found.setUsername(rs.getString("username"));
            user_found.setEmail(rs.getString("email"));
            user_found.setRoles(rs.getString("roles"));
            user_found.setPassword(rs.getString("password"));
            if (BCrypt.checkpw(password, user_found.getPassword().replaceFirst("2y", "2a"))) {
                return user_found;
            } else {
                return null;
            }

        }
        return null;
    }

    public static void insererUtilisateur(FosUser e) {
        String query = "INSERT INTO fos_user(username,username_canonical, email,email_canonical,enabled,roles,type, password, firstName, lastName) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);

            ste.setString(1, e.getUsername());
            ste.setString(2, e.getUsernameCanonical());
            ste.setString(3, e.getEmail());
            ste.setString(4, e.getFirstName());
            ste.setString(5, e.getEmailCanonical());
            ste.setString(6, e.getRoles());
            ste.setString(7, e.getType());
            ste.setString(8, e.getPassword());
            ste.setString(9, e.getFirstName());
            ste.setString(9, e.getLastName());
            ste.setInt(10, 0);
            ste.setInt(11, 0);

            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<FosUser> UsersList() {
        ObservableList data;
        data = FXCollections.observableArrayList();
        try {
            String req = "select * from fos_user";
            ResultSet rs = cnx.createStatement().executeQuery(req);

            while (rs.next()) {
                Offer o = new Offer(rs.getInt("id"), rs.getString("firstName"), rs.getInt("lastName"), rs.getString("email"));
                data.add(o);

            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

}
