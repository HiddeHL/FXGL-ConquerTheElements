module com.hsleiden.conquertheelements {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens assets.textures;
    opens assets.sounds;
    opens assets.levels;

    opens com.hsleiden.conquertheelements to javafx.fxml;
    opens com.hsleiden.conquertheelements.factory to com.almasb.fxgl.core;

    exports com.hsleiden.conquertheelements;
    exports com.hsleiden.conquertheelements.components to com.almasb.fxgl.core;
}