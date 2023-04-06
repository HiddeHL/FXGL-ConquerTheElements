package com.hsleiden.conquertheelements.components;

import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.component.Component;
import com.hsleiden.conquertheelements.ShooterApp;
import kotlin.reflect.KFunction;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerComponent extends Component {
    private int health = 100;
    private int score = 0;

    public boolean takeDamage(int dmg) {
        health-=dmg;
        var hp = entity.getComponent(HealthIntComponent.class);
        hp.damage(dmg);

        if (health <= 0) {
            showMessage("Je bent dood!", () -> {
                getGameController().gotoMainMenu();
            });
            return true;
        }

        return false;
    }

    public void doDamage() {
        score += 10;

        getWorldProperties().setValue("score", score);
    }

    public int getScore() {
        return score;
    }
}
