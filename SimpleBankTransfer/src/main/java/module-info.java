module com.example.simplebanktransfer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.simplebanktransfer to javafx.fxml;
    exports com.example.simplebanktransfer;
}