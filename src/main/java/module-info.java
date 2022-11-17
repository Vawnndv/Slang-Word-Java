module slangword.slangwordjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens slangword.slangwordjava to javafx.fxml;
    exports slangword.slangwordjava;
}