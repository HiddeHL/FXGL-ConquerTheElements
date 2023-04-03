module com.hsleiden.conquertheelements.conquertheelements {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.hsleiden.conquertheelements.conquertheelements to javafx.fxml;
    exports com.hsleiden.conquertheelements.conquertheelements;
}