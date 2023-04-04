package com.hsleiden.conquertheelements.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.hsleiden.conquertheelements.Enums.AnimationState;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class AnimationComponent extends Component {
    private AnimationState animState = AnimationState.IDLE;
    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalkBackward, animWalkSide, animWalkForward;
    private Point2D oldPosition;

    public AnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image("spriteWalk.png"), 8, 24, 32, Duration.seconds(1), 0, 0);
        animWalkBackward = new AnimationChannel(FXGL.image("spriteWalk.png"), 8, 24, 32, Duration.seconds(1), 0, 7);
        animWalkForward = new AnimationChannel(FXGL.image("spriteWalk.png"), 8, 24, 32, Duration.seconds(1), 8, 15);
        animWalkSide = new AnimationChannel(FXGL.image("spriteWalk.png"), 8, 24, 32, Duration.seconds(1), 25, 30);

        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onUpdate(double tpf) {
        if (oldPosition.getY() == entity.getY() && oldPosition.getX() != entity.getX() && animState != AnimationState.WALKINGSIDE) {
            animState = AnimationState.WALKINGSIDE;
            setRightAnimation();
        } else if (oldPosition.getX() == entity.getX() && oldPosition.getY() == entity.getY() && animState != AnimationState.IDLE) {
            animState = AnimationState.IDLE;
            setRightAnimation();
        }

        oldPosition = entity.getPosition();
    }

    public void onAdded() {
        oldPosition = entity.getPosition();

        entity.getViewComponent().addChild(texture);
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
    }

    public void setAnimState(AnimationState newAnimState) {
        if (newAnimState != animState) {
            this.animState = newAnimState;
            setRightAnimation();
        }
    }

    private void setRightAnimation() {
        switch (animState){
            case WALKINGBACKWARD -> texture.loopAnimationChannel(animWalkBackward);
            case WALKINGFORWARD -> texture.loopAnimationChannel(animWalkForward);
            case WALKINGSIDE -> texture.loopAnimationChannel(animWalkSide);
            case IDLE -> texture.loopAnimationChannel(animIdle);
        }
    }
}
