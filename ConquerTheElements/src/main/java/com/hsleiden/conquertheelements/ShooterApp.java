package com.hsleiden.conquertheelements;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.hsleiden.conquertheelements.Enums.EntityType;
import com.hsleiden.conquertheelements.Factory.ShooterEntityFactory;
import com.hsleiden.conquertheelements.components.EnemyComponent;
import com.hsleiden.conquertheelements.components.GunComponent;
import com.hsleiden.conquertheelements.components.MovemementComponent;
import com.hsleiden.conquertheelements.components.PlayerComponent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class ShooterApp extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(1280);
        gameSettings.setHeight(720);
        gameSettings.setTitle("Charachter Controller");
        gameSettings.setVersion("0.1");
        gameSettings.setManualResizeEnabled(false);
        gameSettings.setMainMenuEnabled(true);

        gameSettings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new ShooterMenu();
            }

            @Override
            public FXGLMenu newGameMenu() {
                return new PauseMenu();
            }
        });
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.A, () -> player.getComponent(MovemementComponent.class).moveLeft());
        onKey(KeyCode.D, () -> player.getComponent(MovemementComponent.class).moveRight());
        onKey(KeyCode.W, () -> player.getComponent(MovemementComponent.class).moveForward());
        onKey(KeyCode.S, () -> player.getComponent(MovemementComponent.class).moveBackward());

        onBtnDown(MouseButton.PRIMARY, () -> player.getComponent(GunComponent.class).shoot());
    }

    @Override
    protected void initGame() {
        getSettings().setGlobalSoundVolume(0.1);

        getGameWorld().addEntityFactory(new ShooterEntityFactory());

        spawn("background");

        player = spawn("player", getAppWidth() / 2, getAppHeight() / 2);

        var gameWorld = getGameWorld();
        run(() -> {
            var e = gameWorld.create("enemy", new SpawnData(random(10, getAppWidth()), random(10, getAppHeight())));
            spawnWithScale(e, Duration.seconds(0.3));
        }, Duration.seconds(1));
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.BULLET, EntityType.ENEMY, (bullet, enemy) -> {
            bullet.removeFromWorld();
            enemy.removeFromWorld();
        });

        onCollisionBegin(EntityType.ENEMY, EntityType.PLAYER, (enemy, player) -> {
            int dmg = enemy.getComponent(EnemyComponent.class).getDamage();
            player.getComponent(PlayerComponent.class).takeDamage(dmg);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
