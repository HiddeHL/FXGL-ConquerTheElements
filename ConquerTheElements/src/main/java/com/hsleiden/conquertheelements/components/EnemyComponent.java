package com.hsleiden.conquertheelements.components;

import com.almasb.fxgl.entity.component.Component;

public class EnemyComponent extends Component {
    private int damage;

    public EnemyComponent(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
