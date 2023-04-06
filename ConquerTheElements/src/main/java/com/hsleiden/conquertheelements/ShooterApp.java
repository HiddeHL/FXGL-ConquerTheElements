package com.hsleiden.conquertheelements;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.hsleiden.conquertheelements.Leaderboard.DataController;
import com.hsleiden.conquertheelements.components.*;
import com.hsleiden.conquertheelements.enums.EntityType;
import com.hsleiden.conquertheelements.factory.ShooterEntityFactory;
import javafx.beans.value.ObservableIntegerValue;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.hsleiden.conquertheelements.enums.EntityType.PLAYER;

public class ShooterApp extends GameApplication {

    private Entity player;

    private int MAX_LEVEL = 4;
    private int limit = 0;
    private int numOfSpawnedEnemys;
    private int numOfKilledEnemys;
    private double spawnSpeed;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(1280);
        gameSettings.setHeight(720);
        gameSettings.setTitle("Conquer the Elements");
        gameSettings.setVersion("1.0");
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

            @Override
            public LoadingScene newLoadingScene() {
                return new MainLoadingScene();
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

        player = null;

        limit = 0;
        numOfSpawnedEnemys = 0;
        numOfKilledEnemys = 0;
        spawnSpeed = 1;

        nextLevel();

        set("player", player);
        var gameWorld = getGameWorld();

        run(() -> {
            if (numOfSpawnedEnemys < limit) {
                var e = gameWorld.create("enemy", new SpawnData(random(10, getAppWidth()), random(10, getAppHeight())));
                e.addComponent(new MoveTowardsPlayerComponent(player));
                spawnWithScale(e, Duration.seconds(0.3));
                numOfSpawnedEnemys++;
            }
        }, Duration.seconds(spawnSpeed));
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.BULLET, EntityType.ENEMY, (bullet, enemy) -> {
            bullet.removeFromWorld();
            enemy.removeFromWorld();

            player.getComponent(PlayerComponent.class).doDamage();
            numOfKilledEnemys++;
            getWorldProperties().increment("enemiesLeft", -1);
        });

        onCollisionBegin(PLAYER, EntityType.DOOR, (player, door) -> {
            if (numOfKilledEnemys == limit) {
                nextLevel();
            }
        });


        onCollisionBegin(EntityType.ENEMY, PLAYER, (enemy, player) -> {
            int dmg = enemy.getComponent(EnemyComponent.class).getDamage();
            player.getComponent(PlayerComponent.class).takeDamage(dmg);
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        super.initGameVars(vars);

        vars.put("score", 0);
        vars.put("level", 0);
        vars.put("startTime", System.currentTimeMillis());
        vars.put("enemiesLeft", 0);
    }

    @Override
    protected void initUI() {
        super.initUI();

        Label label = new Label("Enemies left: ");
        label.setStyle("-fx-text-fill: white");
        label.setScaleX(2);
        label.setScaleY(2);
        label.setTranslateX(40);
        label.setTranslateY(10);

        var scoreLabel = new Label("");
        scoreLabel.setStyle("-fx-text-fill: white");
        scoreLabel.setScaleX(2);
        scoreLabel.setScaleY(2);
        scoreLabel.textProperty().bind(getWorldProperties().intProperty("enemiesLeft").asString());

        scoreLabel.setTranslateX(160);
        scoreLabel.setTranslateY(10);

        getGameScene().addUINode(label);
        getGameScene().addUINode(scoreLabel);
    }

    private void nextLevel() {
        inc("level", +1);

        var activeLevel = geti("level");

        if (activeLevel <= MAX_LEVEL) {
            setLevel(activeLevel);
            limit += 15;
            getWorldProperties().setValue("enemiesLeft", limit);
            numOfSpawnedEnemys = 0;
            numOfKilledEnemys = 0;
            spawnSpeed-=0.20;
        } else {
            long finish = System.currentTimeMillis();
            long start = getWorldProperties().getValue("startTime");
            long timeElapsed = (finish - start) / 1000;

            getDialogService().showInputBox("Je hebt gewonnen! \n het duurde " + timeElapsed + " Seconde!", name -> {
                try {
                    DataController writer = new DataController();
                    writer.writeDataToFile(name, numOfKilledEnemys, timeElapsed);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getGameController().gotoMainMenu();
            });
        }
    }

    private void setLevel(int levelNum) {
        if (player != null) {
            player.setZIndex(Integer.MAX_VALUE);
            player.setPosition(new Point2D(50, 50));
        }
        setLevelFromMap("level" + levelNum + ".tmx");
        if (geti("level") == 1) {
            player = spawn("player", getAppWidth() / 2, getAppHeight() / 2);
    } else {
            player = spawn("player", getAppWidth() / 2, 10);
        }
    }

    private void setCamera() {
        Viewport viewport = getGameScene().getViewport();
        viewport.setBounds(-1500, 0, 250 * 70, getAppHeight());
        viewport.bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);
        viewport.setLazy(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
