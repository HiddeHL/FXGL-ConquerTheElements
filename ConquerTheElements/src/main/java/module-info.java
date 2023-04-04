module com.hsleiden.conquertheelements.conquertheelements {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    opens assets.levels;
    opens assets.textures;
    opens assets.levels.tmx;
    opens assets;
    opens com.hsleiden.conquertheelements.conquertheelements to javafx.fxml;
    exports com.hsleiden.conquertheelements.conquertheelements;
}