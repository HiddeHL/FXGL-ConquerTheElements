package com.hsleiden.conquertheelements;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.FXGLScene;
import com.almasb.fxgl.app.scene.GameScene;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;

import com.almasb.fxgl.ui.FontType;
import com.hsleiden.conquertheelements.Leaderboard.Leaderboard;
import com.hsleiden.conquertheelements.Leaderboard.PlayerRecords;
import com.hsleiden.conquertheelements.settings.SettingsSubScene;
import com.hsleiden.conquertheelements.views.MainButton;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.io.IOException;
import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getDialogService;

public class ShooterMenu extends FXGLMenu {
    public ShooterMenu() {
        super(MenuType.MAIN_MENU);

        var vBox = new VBox();
        var titleText = getUIFactoryService().newText("Conquer the Castle", Color.WHITE, FontType.GAME, 100);
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setWrappingWidth(FXGL.getAppWidth() - 100);
        titleText.setX(80);
        titleText.setY(200);

        var button = new MainButton("Start new game", this::fireNewGame);
        var leaderboardButton = new MainButton("leaderboard", () -> {
            try {
                showLeaderboard();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        var quitButton = new MainButton("Quit", this::fireExit);

        button.setPadding(new Insets(8, 8, 8 ,8));
        leaderboardButton.setPadding(new Insets(8, 8, 8 ,8));
        quitButton.setPadding(new Insets(8, 8, 8 ,8));

        vBox.getChildren().add(button);
        vBox.getChildren().add(leaderboardButton);
        vBox.getChildren().add(quitButton);

        var img = new Image("assets/textures/gameBorder.png");
        var imgView = new ImageView(img);

        var extraImg = new Image("assets/textures/screen-lines.jpg");
        var extraImgView = new ImageView(extraImg);

        extraImgView.setFitWidth(getAppWidth());
        extraImgView.setFitHeight(getAppHeight());
        extraImgView.setOpacity(0.4);

        imgView.setFitWidth(getAppWidth());
        imgView.setFitHeight(getAppHeight());

        vBox.setTranslateX(FXGL.getAppWidth() / 2 - 200 / 2);
        vBox.setTranslateY(FXGL.getAppHeight() / 2 - 40 / 2);

        var color = new Color(0.1, 0.1, 0.1, 0.2);
        var background = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, new Insets(0, 0, 0, 0));

        var rectange = new Rectangle();
        rectange.setFill(color);
        rectange.setWidth(getAppWidth());
        rectange.setHeight(FXGL.getAppHeight());
        rectange.setX(0);
        rectange.setY(0);


        getContentRoot().setBackground(new Background(background));
        getContentRoot().getChildren().add(extraImgView);
        getContentRoot().getChildren().add(imgView);
        getContentRoot().getChildren().add(rectange);
        getContentRoot().getChildren().add(titleText);
        getContentRoot().getChildren().add(vBox);
    }

    private void showLeaderboard() throws IOException {
        Leaderboard leaderboard = new Leaderboard();
        ArrayList<PlayerRecords> records = leaderboard.sortLeaderboard();

        var resultString = "Rang \t Kills \t Tijd \t Username \n";

        for (int i = 0; i < records.size() && i < 20; i++) {
            PlayerRecords record = records.get(i);
            resultString += i+1 +". \t\t" + record.getLevel() + "\t\t" + record.getTimeInSeconds() + "\t\t" + record.getUsername() + "\n";
        }

        getDialogService().showMessageBox(resultString);
    }
}
