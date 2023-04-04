package com.hsleiden.conquertheelements.Factory;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.LoadingScene;
import com.hsleiden.conquertheelements.PauseMenu;
import com.hsleiden.conquertheelements.ShooterMenu;
import javafx.scene.control.Label;

public class SceneFactory extends com.almasb.fxgl.app.scene.SceneFactory {
    @Override
    public LoadingScene newLoadingScene() {
        LoadingScene x = super.newLoadingScene();
        Label title = new Label("Conquer The Elements");

        x.addChild(title);
        return x;
    }

    @Override
    public FXGLMenu newMainMenu() {
        return new ShooterMenu();
    }

    @Override
    public FXGLMenu newGameMenu() {
        return new PauseMenu();
    }
}
