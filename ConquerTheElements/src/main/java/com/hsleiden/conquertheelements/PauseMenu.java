package com.hsleiden.conquertheelements;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import com.hsleiden.conquertheelements.views.MainButton;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PauseMenu extends FXGLMenu {
    private Animation<?> animation;

    public PauseMenu() {
        super(MenuType.GAME_MENU);

        var vBox = new VBox();
        MainButton quitButton = new MainButton("Main Menu", () -> {
            FXGL.getGameController().gotoMainMenu();
        });

        MainButton resumeButton = new MainButton("Resume", this::fireResume);

        vBox.getChildren().addAll(quitButton, resumeButton);

        vBox.setTranslateX(FXGL.getAppWidth() / 2 - 200 / 2);
        vBox.setTranslateY(FXGL.getAppHeight() / 2 - 40 / 2);

        getContentRoot().setBackground(Background.fill(Color.color(0, 0, 0, 0.7)));
        getContentRoot().setMinWidth(FXGL.getAppWidth());
        getContentRoot().setMinHeight(FXGL.getAppHeight());

        getContentRoot().getChildren().add(vBox);
        animation = FXGL.animationBuilder()
                .duration(Duration.seconds(0.66))
                .interpolator(Interpolators.EXPONENTIAL.EASE_IN_OUT())
                .scale(getContentRoot())
                .from(new Point2D(0, 0))
                .to(new Point2D(1, 1))
                .build();
    }

    @Override
    public void onCreate() {
        animation.setOnFinished(EmptyRunnable.INSTANCE);
        animation.stop();
        animation.start();
    }

    @Override
    protected void onUpdate(double tpf) {
        animation.onUpdate(tpf);
    }
}