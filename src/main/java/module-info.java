module com.example.lostinthesauce {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lostinthesauce to javafx.fxml;
    exports com.example.lostinthesauce;
}