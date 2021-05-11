/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom4.group4.qlkhachsan;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nhom4.group4.service.JdbcUtils;
import nhom4.group4.pojo.User;
import nhom4.group4.service.UserService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SignupController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private PasswordField txtRePass;
    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    public void Register(ActionEvent evt) {
            User u = new User();
            u.setUsername(txtUser.getText());
            u.setPassword(txtPass.getText());
            Connection conn;
            if(txtUser.getText().isEmpty()){
                Utils.getAlertBox("Chưa nhập username", Alert.AlertType.WARNING).show();
            }
            else if(txtPass.getText().isEmpty()){
                Utils.getAlertBox("Chưa nhập password", Alert.AlertType.WARNING).show();
            } 
            else if(txtRePass.getText().isEmpty()) {
                Utils.getAlertBox("Chưa xác nhận password", Alert.AlertType.WARNING).show();
            }
            else if(!txtRePass.getText().equals(txtPass.getText())) {
                Utils.getAlertBox("Mật khẩu không khớp", Alert.AlertType.WARNING).show();
            }
            else
            try {
            conn = JdbcUtils.getConn();
            UserService s = new UserService(conn);
            if (s.addUser(u) == true) {
                 Utils.getAlertBox("Tạo tài khoản thành công", Alert.AlertType.INFORMATION).show();
            } else
                 Utils.getAlertBox("Không tạo được tài khoản", Alert.AlertType.WARNING).show();
            
             conn.close();
        } catch (SQLException ex) {
             Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    
    @FXML
    private void Return(ActionEvent evt) throws IOException{
        App.setRoot("secondary");
    }
}
