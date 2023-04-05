package com.hsleiden.conquertheelements;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;

import com.hsleiden.conquertheelements.Leaderboard.Leaderboard;
import com.hsleiden.conquertheelements.Leaderboard.PlayerRecords;
import com.hsleiden.conquertheelements.views.MainButton;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getDialogService;

public class ShooterMenu extends FXGLMenu {

    public ShooterMenu() {
        super(MenuType.MAIN_MENU);

        var vBox = new VBox();

        var button = new MainButton("Start new game", this::fireNewGame);
        var leaderboardButton = new MainButton("leaderboard", () -> showLeaderboard());
        var quitButton = new MainButton("Quit", this::fireExit);

        vBox.getChildren().add(button);
        vBox.getChildren().add(leaderboardButton);
        vBox.getChildren().add(quitButton);

        vBox.setTranslateX(FXGL.getAppWidth() / 2 - 200 / 2);
        vBox.setTranslateY(FXGL.getAppHeight() / 2 - 40 / 2);

        getContentRoot().getChildren().add(vBox);
    }

    private void showLeaderboard(){
        System.out.println("dit werkt");
        Leaderboard leaderboard = new Leaderboard();
        ArrayList<PlayerRecords> records = leaderboard.sortLeaderboard();
//        System.out.println(records.size());
//        for(int i = 0; i < records.size(); i++){
//            System.out.println(records.get(i).getUsername() + records.get(i).getLevel() + records.get(i).getTimeInSeconds());
//            System.out.println(i);
//        }

//        System.out.println(records.get(18).getUsername());

        getDialogService().showMessageBox("Rang\tLevel\tTijd\t\tUsername\n" +
                "  1.\t\t" + records.get(0).getLevel() + "\t\t" + records.get(0).getTimeInSeconds() + "\t\t" + records.get(0).getUsername() +"\n" +
                "  2.\t\t" + records.get(1).getLevel() + "\t\t" + records.get(1).getTimeInSeconds() + "\t\t" + records.get(1).getUsername() +"\n" +
                "  3.\t\t" + records.get(2).getLevel() + "\t\t" + records.get(2).getTimeInSeconds() + "\t\t" + records.get(2).getUsername() +"\n" +
                "  4.\t\t" + records.get(3).getLevel() + "\t\t" + records.get(3).getTimeInSeconds() + "\t\t" + records.get(3).getUsername() +"\n" +
                "  5.\t\t" + records.get(4).getLevel() + "\t\t" + records.get(4).getTimeInSeconds() + "\t\t" + records.get(4).getUsername() +"\n" +
                "  6.\t\t" + records.get(5).getLevel() + "\t\t" + records.get(5).getTimeInSeconds() + "\t\t" + records.get(5).getUsername() +"\n" +
                "  7.\t\t" + records.get(6).getLevel() + "\t\t" + records.get(6).getTimeInSeconds() + "\t\t" + records.get(6).getUsername() +"\n" +
                "  8.\t\t" + records.get(7).getLevel() + "\t\t" + records.get(7).getTimeInSeconds() + "\t\t" + records.get(7).getUsername() +"\n" +
                "  9.\t\t" + records.get(8).getLevel() + "\t\t" + records.get(8).getTimeInSeconds() + "\t\t" + records.get(8).getUsername() +"\n" +
                "  10.\t\t" + records.get(9).getLevel() + "\t\t" + records.get(9).getTimeInSeconds() + "\t\t" + records.get(9).getUsername() +"\n" +
                "  11.\t\t" + records.get(10).getLevel() + "\t\t" + records.get(10).getTimeInSeconds() + "\t\t" + records.get(10).getUsername() +"\n" +
                "  12.\t\t" + records.get(11).getLevel() + "\t\t" + records.get(11).getTimeInSeconds() + "\t\t" + records.get(11).getUsername() +"\n" +
                "  13.\t\t" + records.get(12).getLevel() + "\t\t" + records.get(12).getTimeInSeconds() + "\t\t" + records.get(12).getUsername() +"\n" +
                "  14.\t\t" + records.get(13).getLevel() + "\t\t" + records.get(13).getTimeInSeconds() + "\t\t" + records.get(13).getUsername() +"\n" +
                "  15.\t\t" + records.get(14).getLevel() + "\t\t" + records.get(14).getTimeInSeconds() + "\t\t" + records.get(14).getUsername() +"\n" +
                "  16.\t\t" + records.get(15).getLevel() + "\t\t" + records.get(15).getTimeInSeconds() + "\t\t" + records.get(15).getUsername() +"\n" +
                "  17.\t\t" + records.get(16).getLevel() + "\t\t" + records.get(16).getTimeInSeconds() + "\t\t" + records.get(16).getUsername() +"\n" +
                "  18.\t\t" + records.get(17).getLevel() + "\t\t" + records.get(17).getTimeInSeconds() + "\t\t" + records.get(17).getUsername() +"\n" +
                "  19.\t\t" + records.get(18).getLevel() + "\t\t" + records.get(18).getTimeInSeconds() + "\t\t" + records.get(18).getUsername() +"\n" +
                "  20.\t\t" + records.get(19).getLevel() + "\t\t" + records.get(19).getTimeInSeconds() + "\t\t" + records.get(19).getUsername() +"\n"
        );
    }
}
