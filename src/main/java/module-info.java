module slangword.slangwordjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens slangword.slangwordjava to javafx.fxml;
    exports slangword.slangwordjava;
}