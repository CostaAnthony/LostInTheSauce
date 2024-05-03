module com.example.lostinthesauce {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.healthmarketscience.jackcess;
    requires firebase.admin;
    requires com.google.auth;
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires google.cloud.core;
    requires com.google.api.apicommon;
    requires javafx.media;


    opens com.example.lostinthesauce to javafx.fxml, javafx.media;
    exports com.example.lostinthesauce;
}