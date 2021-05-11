/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom4.group4.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import nhom4.group4.pojo.Room;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class RoomService {
    private Connection conn;
    
    public RoomService(Connection conn) {
        this.conn = conn;
    }
    
    public List<Room> getRoom(String kw) throws SQLException {
        if (kw == null)
            throw new SQLDataException("error");
        
        String sql = "SELECT * FROM room WHERE tenphg like concat('%', ?, '%') ORDER BY id DESC";
        PreparedStatement stm = this.getConn().prepareStatement(sql);
        stm.setString(1, kw);
        
        ResultSet rs = stm.executeQuery();
        List<Room> rooms = new ArrayList<>();
        while (rs.next()) {
            Room r = new Room();
            r.setId(rs.getInt("id"));
            r.setTenphg(rs.getString("tenphg"));
            r.setTypeid(rs.getInt("typeid"));
            r.setGia(rs.getInt("gia"));
            r.setMota(rs.getString("mota"));
            
            rooms.add(r);
        }
        
        return rooms;
    }
    
    public boolean addRoom(Room p) {
        try {
            String sql = "INSERT INTO room(tenphg, typeid, gia, mota) VALUES(?, ?, ?, ?)";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1, p.getTenphg());
            stm.setInt(2, p.getTypeid());
            stm.setInt(3, p.getGia());
            stm.setString(4, p.getMota());
            
            int rows = stm.executeUpdate();
            
            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RoomService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean updateRoom(Room p) {
        try {
            String sql = "UPDATE room SET tenphg = ?, typeid = ?, gia = ?, mota = ? WHERE id = ?";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1, p.getTenphg());
            stm.setInt(2, p.getTypeid());
            stm.setInt(3, p.getGia());
            stm.setString(4, p.getMota());
            stm.setInt(5, p.getId());
            int rows = stm.executeUpdate();
            
            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RoomService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean deleteRoom(int roomId) {
        try {
            String sql = "DELETE FROM room WHERE id=?";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1, roomId);
            
            int rows = stm.executeUpdate();
            
            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RoomService.class.getName()).log(Level.SEVERE, null, ex);
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
