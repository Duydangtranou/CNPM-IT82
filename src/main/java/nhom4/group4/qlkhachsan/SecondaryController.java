package nhom4.group4.qlkhachsan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import nhom4.group4.service.JdbcUtils;


public class SecondaryController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPass;

    
    @FXML
    public void Login(ActionEvent evt) throws IOException{

        try {
            Connection conn = JdbcUtils.getConn();
            String sql = "SELECT * FROM user WHERE username =? AND password =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtUser.getText());
            ps.setString(2, txtPass.getText());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                App.setRoot("primary");
                Utils.getAlertBox("Đăng nhập thành công", Alert.AlertType.INFORMATION).show();
            } else {
                Utils.getAlertBox("Lỗi! Hãy đăng nhập lại.", Alert.AlertType.WARNING).show();
            }
        } catch (SQLException ex) {
             Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void Enter(ActionEvent evt) throws IOException{
        App.setRoot("signup");
    }
}