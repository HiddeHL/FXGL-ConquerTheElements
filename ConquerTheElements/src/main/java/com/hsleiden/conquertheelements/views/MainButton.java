package com.hsleiden.conquertheelements.views;

import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainButton extends StackPane {
    public MainButton(String name, Runnable action) {

        var bg = new Rectangle(200, 40);
        bg.setStroke(Color.WHITE);

        var text = FXGL.getUIFactoryService().newText(name, Color.WHITE, 18);

        bg.fillProperty().bind(
                Bindings.when(hoverProperty()).then(Color.GRAY).otherwise(Color.BLACK)
        );

        text.fillProperty().bind(
                Bindings.when(hoverProperty()).then(Color.BLACK).otherwise(Color.GRAY)
        );

        setOnMouseClicked(e -> action.run());

        getChildren().addAll(bg, text);
    }
}
