module com.hsleiden.conquertheelements {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens assets.textures;

    opens com.hsleiden.conquertheelements to javafx.fxml;
    opens com.hsleiden.conquertheelements.factory to com.almasb.fxgl.core;
    exports com.hsleiden.conquertheelements;
}