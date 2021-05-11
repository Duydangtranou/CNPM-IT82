/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom4.group4.service;

import nhom4.group4.pojo.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class TypeService {
        public List<Type> getTypes(String kw) throws SQLException {
        if (kw == null)
            throw new SQLDataException();
        
        List<Type> types;
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM type");
            types = new ArrayList<>();
            while (rs.next()) {
                Type t = new Type();
                t.setTypeid(rs.getInt("typeid"));
                t.setLoaiphong(rs.getString("loaiphong"));
                
                types.add(t);
            }
        }
        return types;
    }
    
   public Type getTypeById(int TypeId) throws SQLException {
       Connection conn = JdbcUtils.getConn();
       String sql ="SELECT * FROM type WHERE typeid=?";
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setInt(1, TypeId);
       
       ResultSet rs = stm.executeQuery();
       
       Type t = null;
       while (rs.next()) {
           t = new Type();
           t.setTypeid(rs.getInt("typeid"));
           t.setLoaiphong(rs.getString("loaiphong"));
           break;
       }
       
       return t;
   }
}
