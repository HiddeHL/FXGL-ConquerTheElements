package com.hsleiden.conquertheelements.conquertheelements;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.hsleiden.conquertheelements.conquertheelements.Leaderboard.Leaderboard;
import com.hsleiden.conquertheelements.conquertheelements.Leaderboard.PlayerRecords;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.ArrayList;


public class ShowLeaderboard extends GameApplication {


    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(1280);
        gameSettings.setHeight(720);
    }


    @Override
    protected void initUI() {
        Leaderboard leaderboard = new Leaderboard();
        try {
            VBox vBox = new VBox();
            ScrollPane sp = new ScrollPane();
            ArrayList<PlayerRecords> records = leaderboard.sortLeaderboard();
            
            for (int i = 0; i < records.size(); i++) {
                HBox hBox = new HBox(20);
//                hBox.setPadding(new Insets(20, 20, 20, 20));
                Label plaats = new Label("\t" + String.valueOf(i + 1) + "\t  ");
//                plaats.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(20), Insets.EMPTY)));
                Label userName = new Label(records.get(i).getUsername());
                Label level = new Label(String.valueOf(records.get(i).getLevel()));
                Label time = new Label(String.valueOf(records.get(i).getTimeInSeconds()));
                hBox.getChildren().addAll(plaats, userName, level, time);
                hBox.setTranslateX(FXGL.getAppWidth() / 2 - 100);
                vBox.getChildren().add(hBox);
            }

            FXGL.addUINode(vBox, 20, 20);
            sp.setContent(vBox);

            sp.setPrefSize(FXGL.getAppWidth(), FXGL.getAppHeight());
            sp.setStyle("-fx-background: black");
            FXGL.addUINode(sp);
            System.out.println(vBox.getWidth());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }


}