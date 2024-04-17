module com.vichito.polimorfismovic {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vichito.polimorfismovic to javafx.fxml;
    exports com.vichito.polimorfismovic;
    exports com.vichito.polimorfismovic.Models;
    opens com.vichito.polimorfismovic.Models to javafx.fxml;
    exports com.vichito.polimorfismovic.Controllers;
    opens com.vichito.polimorfismovic.Controllers to javafx.fxml;
}