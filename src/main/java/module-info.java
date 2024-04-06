module com.example.lostinthesauce {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.healthmarketscience.jackcess;


    opens com.example.lostinthesauce to javafx.fxml;
    exports com.example.lostinthesauce;
}
