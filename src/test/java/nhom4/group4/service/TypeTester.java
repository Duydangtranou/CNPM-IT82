/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom4.group4.service;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nhom4.group4.pojo.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author DELL
 */
public class TypeTester {
    @Test
    @DisplayName("Kiểm thử số lượng danh mục >= 3")
    public void testQuantity() throws SQLException {
        TypeService s = new TypeService();
        List<Type> types = s.getTypes("");
        
        Assertions.assertTrue(types.size() >= 3);
    }
    
    @Test
    @DisplayName("Kiểm thử tên danh mục phải duy nhất")
    public void testUniqueName() throws SQLException {
        TypeService s = new TypeService();
        List<Type> cates = s.getTypes("");
        
        List<String> names = new ArrayList<>();
        cates.forEach(c -> names.add(c.getLoaiphong()));
        
        Set<String> temp = new HashSet<>(names);
        
        Assertions.assertEquals(names.size(), temp.size());
    }
    
    @Test
    @DisplayName("Kiểm tra ném đúng loại ngoại lệ mong muốn")
    public void testException() {
        Assertions.assertThrows(SQLDataException.class, () -> {
            TypeService s = new TypeService();
            s.getTypes(null);
        });
    }
    
    @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            TypeService s = new TypeService();
            s.getTypes("");
        });
    }
}
