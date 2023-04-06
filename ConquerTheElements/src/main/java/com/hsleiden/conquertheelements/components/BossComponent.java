package com.hsleiden.conquertheelements.components;

import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.hsleiden.conquertheelements.enums.EntityType;
import javafx.geometry.Rectangle2D;

import java.util.concurrent.ThreadLocalRandom;

import static com.almasb.fxgl.core.math.FXGLMath.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static java.lang.Math.random;

public class BossComponent extends Component {
    private int damage;

    public BossComponent(int damage, int health) {
        this.damage = damage;
    }

    public int getAmountOfDmg() {
        return damage;
    }

    public void doDamage(int dmg) {
        entity.getComponent(HealthIntComponent.class).damage(dmg);
        System.out.println(entity.getComponent(HealthIntComponent.class).getValue());
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);

        try {
            Entity bullet = entity.getWorld().getSingleton(EntityType.BULLET);
            Rectangle2D hitBox = new Rectangle2D(entity.getWidth() + 150, entity.getHeight() + 150, entity.getX(), entity.getY());

            if (bullet.isWithin(hitBox)) {
                if (true) {
                    if (entity.getX() > 300) {
                            System.out.println("DODGE LEFT");
                            entity.setX(entity.getX() - 200);
                    } else if (entity.getY() > 300) {
                        System.out.println("DODGE UP");
                        entity.setY(entity.getY() - 200);
                    } else if (entity.getX() > 200 && entity.getX() < getAppWidth() - 200) {
                        System.out.println("DODGE RIGHT");
                        entity.setX(entity.getX() + 200);
                    } else if (entity.getY() < getAppHeight() - 300 && entity.getY() > 300) {
                            System.out.println("DODGE DOWN");
                            entity.setY(entity.getY() + 200);
                    } else {
                        entity.setY(entity.getY() - 200);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("NOT IN RANGE");
        }
    }
}
