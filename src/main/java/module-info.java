module com.vichito.polimorfismovic {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vichito.polimorfismovic to javafx.fxml;
    exports com.vichito.polimorfismovic;
}