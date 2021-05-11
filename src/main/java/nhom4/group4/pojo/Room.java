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
public class Room {
    private int id;
    private String tenphg;
    private int typeid;
    private int gia;
    private String mota;
    
    @Override
    
    public String toString() {
        return this.tenphg;
    }

    /**
     * @param id the name to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @param tenphg the name to set
     */
    public void setTenphg(String tenphg) {
        this.tenphg = tenphg;
    }
    
    /**
     * @param typeid the name to set
     */
    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }
    
    /**
     * @param gia the name to set
     */
    public void setGia(int gia) {
        this.gia = gia;
    }
    
    /**
     * @param mota the name to set
     */
    public void setMota(String mota) {
        this.mota = mota;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * @return the tenphg
     */
    public String getTenphg() {
        return tenphg;
    }
    
    /**
     * @return the typeid
     */
    public int getTypeid() {
        return typeid;
    }

    /**
     * @return the gia
     */
    public int getGia() {
        return gia;
    }

    /**
     * @return the mota
     */
    public String getMota() {
        return mota;
    }
    
}
