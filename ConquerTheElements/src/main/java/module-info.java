module com.hsleiden.conquertheelements.conquertheelements {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.hsleiden.conquertheelements to javafx.fxml;
    opens com.hsleiden.conquertheelements.Factory to com.almasb.fxgl.core;
    exports com.hsleiden.conquertheelements;
}