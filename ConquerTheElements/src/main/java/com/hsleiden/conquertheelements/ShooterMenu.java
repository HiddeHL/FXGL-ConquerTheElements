package com.hsleiden.conquertheelements;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.hsleiden.conquertheelements.Views.MainButton;
import javafx.scene.layout.VBox;

public class ShooterMenu extends FXGLMenu {

    public ShooterMenu() {
        super(MenuType.MAIN_MENU);

        var vBox = new VBox();

        var button = new MainButton("Start new game", this::fireNewGame);
        var quitButton = new MainButton("Quit", this::fireExit);

        vBox.getChildren().add(button);
        vBox.getChildren().add(quitButton);

        vBox.setTranslateX(FXGL.getAppWidth() / 2 - 200 / 2);
        vBox.setTranslateY(FXGL.getAppHeight() / 2 - 40 / 2);

        getContentRoot().getChildren().add(vBox);
    }
}
