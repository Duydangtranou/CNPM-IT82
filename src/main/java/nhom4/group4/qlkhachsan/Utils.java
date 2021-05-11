/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom4.group4.qlkhachsan;

import javafx.scene.control.Alert;

/**
 *
 * @author DELL
 */
public class Utils {
    public static Alert getAlertBox(String content, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setTitle("Thông Báo");
        a.setHeaderText("Thông Báo");
        a.setContentText(content);
        
        return a;
    }
}
