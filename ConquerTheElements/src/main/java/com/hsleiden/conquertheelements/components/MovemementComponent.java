package com.hsleiden.conquertheelements.components;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.hsleiden.conquertheelements.enums.AnimationState;

public class MovemementComponent extends Component {
    private int movementSpeed = 5;

    public void onAdded() {
        entity.setScaleUniform(1.5);
    }

    public void moveLeft() {
        entity.translateX(-movementSpeed);
        entity.setScaleX(-1.5);
    }

    public void moveRight() {
        entity.translateX(movementSpeed);
        entity.setScaleX(1.5);
    }

    public void moveForward() {
        Vec2 dir = Vec2.fromAngle(entity.getRotation() - 90)
                .mulLocal(movementSpeed);
        entity.translate(dir);

        setNewAnimationState(AnimationState.WALKINGFORWARD);
    }
    public void moveBackward() {
        Vec2 dir = Vec2.fromAngle(entity.getRotation() - 90)
                .mulLocal(-movementSpeed);
        entity.translate(dir);

        setNewAnimationState(AnimationState.WALKINGBACKWARD);
    }

    private void setNewAnimationState(AnimationState newAnimationState) {
        entity.getComponent(AnimationComponent.class).setAnimState(newAnimationState);
    }
}
