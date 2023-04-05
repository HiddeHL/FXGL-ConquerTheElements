package com.hsleiden.conquertheelements.factory;

import com.almasb.fxgl.dsl.components.*;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.TimeComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.ui.ProgressBar;
import com.hsleiden.conquertheelements.enums.EntityType;
import com.hsleiden.conquertheelements.components.*;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.*;

public class ShooterEntityFactory implements EntityFactory {
    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        Rectangle rect = new Rectangle(getAppWidth(), getAppHeight());

        rect.setFill(Color.BLACK);

        return entityBuilder(data)
                .view(rect)
                .build();
    }

    @Spawns("background-img")
    public Entity newBackgroundImg(SpawnData data) {
        Rectangle rect = new Rectangle(getAppWidth(), getAppHeight());

        Image image = new Image("level1_background");
        BackgroundImage bi = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );

        return entityBuilder(data)
                .view(rect)
                .build();
    }

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        var hp = new HealthIntComponent(100);

        var hpView = new ProgressBar(false);
        hpView.setFill(Color.LIGHTGREEN);
        hpView.setMaxValue(100);
        hpView.setWidth(40);
        hpView.setTranslateY(-20);
        hpView.setTranslateX(-8);
        hpView.currentValueProperty().bind(hp.valueProperty());

        Rectangle hitbox = new Rectangle();

        hitbox.setHeight(20);
        hitbox.setWidth(15);

        hitbox.setFill(Color.color(0, 0, 0, 0));

        return entityBuilder(data)
                .type(EntityType.PLAYER)
                .viewWithBBox(hitbox)
                .collidable()
                .view(hpView)
                .with(hp)
                .with(new PlayerComponent())
                .with(new MovemementComponent())
                .with(new GunComponent())
                .with(new AnimationComponent())
                .with(new KeepInBoundsComponent(new Rectangle2D(0, 0, 1260, 700)))
                .build();
    }

    @Spawns("bullet")
    public Entity newBullet(SpawnData data) {
        Rectangle bullet = new Rectangle(2, 20);
        Point2D dir = data.get("dir");

        bullet.setFill(Color.YELLOW);
        bullet.setRotate(90);

        return entityBuilder(data)
                .type(EntityType.BULLET)
                .viewWithBBox(bullet)
                .view("firedrop2.png")
                .scale(0.2, 0.2)
                .with(new ProjectileComponent(dir, 500))
                .with(new OffscreenCleanComponent())
                .with(new TimeComponent())
                .with(new EffectComponent())
                .collidable()
                .build();
    }

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(EntityType.ENEMY)
                .viewWithBBox("blue_ghost40.png")
                .collidable()
                .with(new EnemyComponent(50))
                .build();
    }

    @Spawns("wall")
    public Entity newWall(SpawnData data) {
        int width = data.get("width");
        int height = data.get("height");
        return entityBuilder(data)
                .type(EntityType.WALL)
                .bbox(new HitBox("main", BoundingShape.box(width, height)))
                .collidable()
                .build();
    }

    @Spawns("door")
    public Entity newDoor(SpawnData data) {
        int width = data.get("width");
        int height = data.get("height");
        return entityBuilder(data)
                .type(EntityType.DOOR)
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .bbox(new HitBox("main", BoundingShape.box(width, height)))
                .collidable()
                .build();
    }
}
