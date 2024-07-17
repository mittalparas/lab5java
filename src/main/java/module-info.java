module com.example.lab5java {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.lab5java to javafx.fxml;
    exports com.example.lab5java;
}