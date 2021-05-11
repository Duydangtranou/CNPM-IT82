/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom4.group4.service;

import nhom4.group4.pojo.Room;
import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class RoomTester {
    private static Connection conn;
    
    @BeforeAll
    public static void setUpClass() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(RoomTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoomTester.class.getName()).log(Level.SEVERE, null, ex);
            };
    }
      
    @Test
    public void testWithKeyword() throws SQLException {
        RoomService s = new RoomService(conn);
        List<Room> rooms = s.getRoom("300");
        
        rooms.forEach(p -> {
            Assertions.assertTrue(p.getTenphg().toLowerCase().contains("300"));
        });
    }
    
    @Test
    public void testWithUnknowKeyword() throws SQLException {
        RoomService s = new RoomService(conn);
        List<Room> rooms = s.getRoom("765A");
        
        Assertions.assertEquals(0, rooms.size());
    }
    
    @Test
    public void testException() throws SQLException {
        Assertions.assertThrows(SQLDataException.class, () -> {
            RoomService s = new RoomService(conn);
             List<Room> rooms = s.getRoom(null);
        });
    }
    
    @Test
    @DisplayName("...")
    @Tag("add-product")
    public void testAddRoomWithInvalidCateId() {
        RoomService s = new RoomService(conn);
        
        Room r = new Room();
        r.setTenphg("105");
        r.setGia(Integer.parseInt("28000"));
        r.setTypeid(999);
        
        Assertions.assertFalse(s.addRoom(r));
    }
    
    @Test
    @DisplayName("...")
    @Tag("add-product")
    public void testAddRoomWithInvalidName() {
        RoomService s = new RoomService(conn);
        
        Room r = new Room();
        r.setTenphg(null);
        r.setGia(Integer.parseInt("28000"));
        r.setTypeid(1);
        
        Assertions.assertFalse(s.addRoom(r));
    }
    
    @Test
    @DisplayName("...")
    @Tag("add-product")
    public void testAddRoom() {
        RoomService s = new RoomService(conn);
        
        Room r = new Room();
        r.setTenphg("289");
        r.setGia(Integer.parseInt("28000"));
        r.setTypeid(1);
        
        Assertions.assertTrue(s.addRoom(r));
    }
    
    @ParameterizedTest
    @CsvSource({"R1,100,9999,false", ",100,9999,false", "R1,280,2,true"})
    public void testDataSource(String name, int price, int id, boolean expected) {
        RoomService s = new RoomService(conn);
        
        Room r = new Room();
        r.setTenphg(name);
        r.setGia(price);
        r.setTypeid(id);
        
        Assertions.assertEquals(expected, s.addRoom(r));
    }

}
