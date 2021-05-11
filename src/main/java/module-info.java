module nhom4.group4.qlkhachsan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens nhom4.group4.qlkhachsan to javafx.fxml;
    exports nhom4.group4.qlkhachsan;
    exports nhom4.group4.pojo;
}
