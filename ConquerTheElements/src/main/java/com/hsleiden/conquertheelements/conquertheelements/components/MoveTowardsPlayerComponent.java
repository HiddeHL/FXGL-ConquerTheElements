package com.hsleiden.conquertheelements.conquertheelements.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

public class MoveTowardsPlayerComponent extends Component {
    private final Entity player;

    public MoveTowardsPlayerComponent(Entity player) {
        this.player = player;
    }

    @Override
    public void onUpdate(double tpf) {
        Point2D playerPosition = player.getPosition();
        Point2D enemyPosition = entity.getPosition();

        // Calculate the direction vector from enemy to player
        Point2D direction = playerPosition.subtract(enemyPosition).normalize();

        // Set the enemy's velocity to move towards the player
        entity.translate(direction.multiply(tpf * 100));    }
}
