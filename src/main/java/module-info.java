module com.example.blinkitsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.blinkitsystem to javafx.fxml;
    exports com.example.blinkitsystem;
}