package com.hsleiden.conquertheelements.Factory;

import com.almasb.fxgl.dsl.components.*;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.TimeComponent;
import com.almasb.fxgl.ui.ProgressBar;
import com.hsleiden.conquertheelements.Enums.EntityType;
import com.hsleiden.conquertheelements.components.*;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
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
                .with(new ProjectileComponent(dir, 500))
                .with(new OffscreenCleanComponent())
                .with(new TimeComponent())
                .with(new EffectComponent())
                .collidable()
                .build();
    }

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        Circle circle = new Circle(20, 20, 20, Color.RED);
        circle.setStroke(Color.BROWN);
        circle.setStrokeWidth(2.0);

        Entity enemy = entityBuilder()
                .from(data)
                .type(EntityType.ENEMY)
                .viewWithBBox(circle)
                .collidable()
                .with(new RandomMoveComponent(
                        new Rectangle2D(0, 0,
                                getAppWidth(), getAppHeight()), 50))
                .with(new EnemyComponent(50))
                .build();

        return enemy;
    }
}
