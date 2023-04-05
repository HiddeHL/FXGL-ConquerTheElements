package com.hsleiden.conquertheelements;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.FXGLScene;
import com.almasb.fxgl.app.scene.GameScene;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.hsleiden.conquertheelements.views.MainButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import static com.almasb.fxgl.dsl.FXGL.*;

public class ShooterMenu extends FXGLMenu {
    GameApplication leaderboard = new ShowLeaderboard();
//    BackgroundSize x = new BackgroundSize(getAppWidth(), FXGL.getAppHeight());
//    BackgroundImage bgImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

    public ShooterMenu() {
        super(MenuType.MAIN_MENU);

        var vBox = new VBox();

        var button = new MainButton("Start new game", this::fireNewGame);
        var leaderboardButton = new MainButton("Leaderboard", () -> {
        });
        var quitButton = new MainButton("Quit", this::fireExit);

        vBox.getChildren().add(button);
        vBox.getChildren().add(leaderboardButton);
        vBox.getChildren().add(quitButton);

        var img = new Image("assets/textures/gameBorder.png");
        var imgView = new ImageView(img);

        imgView.setFitWidth(getAppWidth());
        imgView.setFitHeight(getAppHeight());

        vBox.setTranslateX(FXGL.getAppWidth() / 2 - 200 / 2);
        vBox.setTranslateY(FXGL.getAppHeight() / 2 - 40 / 2);

//        getContentRoot().setBackground(new Background(bgImg));


        getContentRoot().getChildren().add(vBox);
        getContentRoot().getChildren().add(imgView);
    }


}
