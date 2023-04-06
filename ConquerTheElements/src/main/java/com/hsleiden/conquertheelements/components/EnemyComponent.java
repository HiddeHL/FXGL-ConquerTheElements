package com.hsleiden.conquertheelements.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.hsleiden.conquertheelements.enums.EntityType;
import javafx.geometry.Rectangle2D;

public class EnemyComponent extends Component {
    private int damage;

    public EnemyComponent(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
