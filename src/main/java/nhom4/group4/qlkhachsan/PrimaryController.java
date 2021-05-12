package nhom4.group4.qlkhachsan;

import java.io.IOException;
import nhom4.group4.pojo.Room;
import nhom4.group4.pojo.Type;
import nhom4.group4.service.JdbcUtils;
import nhom4.group4.service.RoomService;
import nhom4.group4.service.TypeService;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable {
    @FXML private ComboBox<Type> cbCategories;
    @FXML private TableView<Room> tbRooms;
    @FXML private TextArea  txtDesc;
    @FXML private TextField txtName;
    @FXML private TextField txtPrice;
    @FXML private TextField txtFind;
    @FXML private Text TxtAlert;
    
    @FXML
    private void Return(ActionEvent evt) throws IOException{
        App.setRoot("secondary");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            TypeService s = new TypeService();
            List<Type> types = s.getTypes("");
            
            this.cbCategories.setItems(FXCollections.observableList(types));
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadColumns();
        loadData("");
        
        this.txtFind.textProperty().addListener((obj) -> {
            loadData(this.txtFind.getText());
        });
        
        
        this.tbRooms.setRowFactory(obj -> {
            TableRow row = new TableRow();
            
            row.setOnMouseClicked(evt -> {
                try {
                    Room p = this.tbRooms.getSelectionModel().getSelectedItem();
                    txtName.setText(p.getTenphg());
                    txtPrice.setText(String.valueOf(p.getGia()));
                    txtDesc.setText(p.getMota());                 
                    Type t = new TypeService().getTypeById(p.getTypeid());      
                    this.cbCategories.getSelectionModel().select(t);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            return row;
        });
        
    }
    
    @FXML
    public void updateRoom(ActionEvent evt) {
        
        Room r = new Room();
        Room p = this.tbRooms.getSelectionModel().getSelectedItem();
        if(ValidateFields()){
            r.setTenphg(txtName.getText());
            r.setGia(Integer.parseInt(txtPrice.getText()));
            r.setMota(txtDesc.getText());
            Type c = this.cbCategories.getSelectionModel().getSelectedItem();
            r.setTypeid(c.getTypeid());
            r.setId(p.getId());
            Connection conn;
            try {
            conn = JdbcUtils.getConn();
            
            RoomService s = new RoomService(conn);
            if (s.updateRoom(r) == true) {
                 Utils.getAlertBox("Sửa phòng thành công", Alert.AlertType.INFORMATION).show();
                 loadData("");
            } else
                 Utils.getAlertBox("Lỗi", Alert.AlertType.WARNING).show();
            
             conn.close();
            } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }               
    }
    
    @FXML
    public void addRoom(ActionEvent evt) {
        
        Room r = new Room();
        if(ValidateFields()){
            r.setTenphg(txtName.getText());
            r.setGia(Integer.parseInt(txtPrice.getText()));
            r.setMota(txtDesc.getText());
            Type c = this.cbCategories.getSelectionModel().getSelectedItem();
            if(cbCategories.getSelectionModel().isEmpty()){
                TxtAlert.setText("Chưa chọn loại phòng");
            }
            r.setTypeid(c.getTypeid());
            Connection conn;
            try {
            conn = JdbcUtils.getConn();
            
            RoomService s = new RoomService(conn);
            if (s.addRoom(r) == true) {
                 Utils.getAlertBox("Thêm phòng thành công", Alert.AlertType.INFORMATION).show();
                 loadData("");
            } else
                 Utils.getAlertBox("Lỗi", Alert.AlertType.WARNING).show();
            
             conn.close();
            } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }               
    }
    
    private void loadColumns() {;
        
        TableColumn colName = new TableColumn("Tên phòng");
        colName.setPrefWidth(80);
        colName.setCellValueFactory(new PropertyValueFactory("tenphg"));
        
        TableColumn colPrice = new TableColumn("Gía phòng");
        colPrice.setPrefWidth(80);
        colPrice.setCellValueFactory(new PropertyValueFactory("gia"));
        
        TableColumn colDesc = new TableColumn("Mô tả");
        colDesc.setPrefWidth(120);
        colDesc.setCellValueFactory(new PropertyValueFactory("mota"));
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory((obj) -> {
            Button btn = new Button("Xóa");
            
            btn.setOnAction(evt -> {
                Utils.getAlertBox("Bạn chắc chắn xóa không?", Alert.AlertType.CONFIRMATION)
                        .showAndWait().ifPresent(bt -> {
                    if (bt == ButtonType.OK) {
                        try {
                            TableCell cell = (TableCell)((Button) evt.getSource()).getParent();
                            Room r = (Room) cell.getTableRow().getItem();
                            
                            try (Connection conn = JdbcUtils.getConn()) {
                                RoomService s = new RoomService(conn);
                                if (s.deleteRoom(r.getId()) == true) {
                                    Utils.getAlertBox("Xóa thành công", Alert.AlertType.INFORMATION).show();
                                    this.loadData("");
                                } else {
                                    Utils.getAlertBox("Lỗi", Alert.AlertType.ERROR).show();
                                }
                            }
                            
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
                });
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        this.tbRooms.getColumns().addAll( colName, colPrice, colDesc, colAction);
    }
    
    private void loadData(String kw) {
        try {
            Connection conn = JdbcUtils.getConn();
            RoomService s = new RoomService(conn);
            List<Room> rooms = s.getRoom(kw);
            this.tbRooms.setItems(FXCollections.observableList(rooms));
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean ValidateFields() {
        
        if(txtName.getText().isEmpty()){
                TxtAlert.setText("Chưa nhập tên phòng");
                return false;
            }
        else if(txtPrice.getText().isEmpty()){
                TxtAlert.setText("Chưa nhập giá");
                return false;
            }
        else if(!txtPrice.getText().matches("[0-9]+")){
                TxtAlert.setText("Giá trị không hợp lệ");
                return false;
            }
         return true;
    }
}
