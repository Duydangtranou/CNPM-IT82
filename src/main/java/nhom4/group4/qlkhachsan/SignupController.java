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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    @FXML
    private TextField txtEmail;
    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    public void Register(ActionEvent evt) {
            User u = new User();
            u.setUsername(txtUser.getText());
            u.setPassword(txtPass.getText());
            u.setEmail(txtEmail.getText());
            Connection conn;
            if(ValidateFields()){
                 if(ValidPassword(txtPass.getText()))
                    {
                    try {
                    conn = JdbcUtils.getConn();
                    UserService s = new UserService(conn);
                    if (s.addUser(u) == true) {
                         Utils.getAlertBox("Tạo tài khoản thành công", Alert.AlertType.INFORMATION).show();
                         txtUser.clear();
                         txtEmail.clear();
                         txtPass.clear();
                         txtRePass.clear();
                    } else
                         Utils.getAlertBox("Không tạo được tài khoản", Alert.AlertType.WARNING).show();

                     conn.close();
                    } catch (SQLException ex) {
                     Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            else
                Utils.getAlertBox("Phải có 6 ký tự trở lên, ít nhất 1 số, 1 chữ hoa và thường, ko đb", Alert.AlertType.INFORMATION).show();
        }
    }
    
    private boolean ValidPassword(String password)
    {
  
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?!.*[@#$%^&+=])"
                       + "(?=\\S+$).{6,36}$";
        Pattern p = Pattern.compile(regex);

        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
    
    private boolean ValidateFields() { 
        if(txtUser.getText().isEmpty()){
                Utils.getAlertBox("Chưa nhập username", Alert.AlertType.WARNING).show();
                return false;
            }
        else if(txtUser.getText().length() <= 1){
                Utils.getAlertBox("Username không được nhập 1 ký tự", Alert.AlertType.WARNING).show();
                return false;
            }
        else if(txtEmail.getText().isEmpty()){
                Utils.getAlertBox("Chưa nhập email", Alert.AlertType.WARNING).show();
                return false;
            }
        else if(!txtEmail.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
                Utils.getAlertBox("Sai cú pháp", Alert.AlertType.WARNING).show();
                return false;
            }
        else if(txtPass.getText().isEmpty()){
                Utils.getAlertBox("Chưa nhập password", Alert.AlertType.WARNING).show();
                return false;
            }
        else if(txtRePass.getText().isEmpty()){
                Utils.getAlertBox("Chưa nhập lại password", Alert.AlertType.WARNING).show();
                return false;
            }
        else if(!txtRePass.getText().equals(txtPass.getText())) {
                Utils.getAlertBox("Mật khẩu không khớp", Alert.AlertType.WARNING).show();
                return false;
            }
        return true;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    
    @FXML
    private void Return(ActionEvent evt) throws IOException{
        App.setRoot("secondary");
    }
}
