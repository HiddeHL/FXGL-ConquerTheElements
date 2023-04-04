package com.hsleiden.conquertheelements.components;

import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.*;

public class GunComponent extends Component {
    public void shoot() {
        Point2D center = entity.getCenter();
        Point2D dir = getInput().getVectorToMouse(entity.getPosition());

        getAudioPlayer().playSound(getAssetLoader().loadSound("fireball.mp3"));

        spawn("bullet", new SpawnData(center.getX(), center.getY()).put("dir", dir));
    }
}
