package com.hsleiden.conquertheelements.components;

import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class GunComponent extends Component {
    public void shoot() {
        Point2D center = entity.getCenter();
        Point2D dir = getInput().getVectorToMouse(entity.getPosition());

        spawn("bullet", new SpawnData(center.getX(), center.getY()).put("dir", dir));
    }
}
