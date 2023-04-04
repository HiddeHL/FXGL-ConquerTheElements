package com.hsleiden.conquertheelements.conquertheelements;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.MenuItem;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.EnumSet;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

public class ShootieApp extends GameApplication {

    private final ShootieFactory factory = new ShootieFactory();
    public static Entity player;

    @Override
    protected void initInput() {
        onKey(KeyCode.W, () -> player.translateY(-5));
        onKey(KeyCode.A, () -> player.translateX(-5));
        onKey(KeyCode.S, () -> player.translateY(5));
        onKey(KeyCode.D, () -> player.translateX(5));
        onBtnDown(MouseButton.PRIMARY, () -> spawn("bullet", player.getCenter()));
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(factory);

        player = spawn("player", getAppWidth() / 2 - 15, getAppHeight() / 2 - 15);
        //ShootieFactory.setPlayer(player);

        run(() -> spawn("enemy"), Duration.seconds(1.0));
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.BULLET, EntityType.ENEMY, (bullet, enemy) -> {
            bullet.removeFromWorld();
            enemy.removeFromWorld();
        });

        onCollisionBegin(EntityType.ENEMY, EntityType.PLAYER, (enemy, player) -> {
            showMessage("Je bent dood!", () -> {
                getGameController().startNewGame();
            });
        });
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Test Game pogchamp");
        settings.setVersion("0.1");
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setProfilingEnabled(false);
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);
        settings.setManualResizeEnabled(true);
        settings.setPreserveResizeRatio(true);
        settings.setIntroEnabled(false);
        settings.setEnabledMenuItems(EnumSet.of(MenuItem.EXTRA));
        settings.getCredits().addAll(Arrays.asList(
                "Music by Eric Matyas",
                "www.soundimage.org"
        ));
        settings.setApplicationMode(ApplicationMode.RELEASE);
    }

    public enum EntityType {
        PLAYER, ENEMY, BULLET
    }

    public static Entity getPlayer() {
        return player;
    }

    public static void main(String[] args) {
        launch(args);
    }
}