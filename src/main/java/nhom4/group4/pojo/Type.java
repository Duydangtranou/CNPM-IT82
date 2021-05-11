/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom4.group4.pojo;

/**
 *
 * @author DELL
 */
public class Type {
    private int typeid;
    private String loaiphong;
    
    @Override
    public String toString() {
        return this.loaiphong;
    }

    public int getTypeid() {
        return typeid;
    }

    public String getLoaiphong() {
        return loaiphong;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public void setLoaiphong(String loaiphong) {
        this.loaiphong = loaiphong;
    }
    
    
}

    
