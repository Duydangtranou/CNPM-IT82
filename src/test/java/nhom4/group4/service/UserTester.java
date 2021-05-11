/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom4.group4.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nhom4.group4.pojo.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author DELL
 */
public class UserTester {
        private static Connection conn;
    
    @BeforeAll
    public static void setUpClass() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(UserTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserTester.class.getName()).log(Level.SEVERE, null, ex);
            };
    }
    
    @Test
    @DisplayName("...")
    @Tag("add-product")
    public void testAddUserWithInvalidUsername() {
        UserService s = new UserService(conn);
        
        User usr = new User();
        usr.setUsername(null);
        usr.setPassword("123456");
        
        Assertions.assertFalse(s.addUser(usr));
    }
    
    @Test
    @DisplayName("...")
    @Tag("add-product")
    public void testAddUserWithInvalidPassword() {
        UserService s = new UserService(conn);
        
        User usr = new User();
        usr.setUsername("Test");
        usr.setPassword(null);
        
        Assertions.assertFalse(s.addUser(usr));
    }
    
    @Test
    @DisplayName("...")
    @Tag("add-product")
    public void testAddRoom() {
        UserService s = new UserService(conn);
        
        User usr = new User();
        usr.setUsername("Test");
        usr.setPassword("123456");
        
        Assertions.assertTrue(s.addUser(usr));
    }
    
    @ParameterizedTest
    @CsvSource({"U1,,false", ",123456,false", "U1,123456,true"})
    public void testDataSource(String name, String pass, boolean expected) {
        UserService s = new UserService(conn);
        
        User usr = new User();
        usr.setUsername(name);
        usr.setPassword(pass);
        
        Assertions.assertFalse(s.addUser(usr));
    }
    
}
