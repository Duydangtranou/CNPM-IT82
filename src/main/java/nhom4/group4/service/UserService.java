/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom4.group4.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nhom4.group4.pojo.User;

/**
 *
 * @author DELL
 */
public class UserService {
    private Connection conn;
    
    public UserService (Connection conn) {
        this.conn = conn;
    }
    
    public boolean addUser(User u) {
        try {
            String sql = "INSERT INTO user(username, password, email) VALUES(?, ?, ?)";
            PreparedStatement psm = this.conn.prepareStatement(sql);
            psm.setString(1, u.getUsername());
            psm.setString(2, u.getPassword());
            psm.setString(3, u.getEmail());   
            int rows = psm.executeUpdate();
            
            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Connection getConn() {
        return conn;
    }
    
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
