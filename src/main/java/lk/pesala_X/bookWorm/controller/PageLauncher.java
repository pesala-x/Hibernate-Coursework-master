package lk.pesala_X.bookWorm.controller;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class PageLauncher {

    private PageLauncher(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    private PageLauncher(String path , Button btn){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        Stage currentStage = (Stage) btn.getScene().getWindow();
        currentStage.close();
    }

    private PageLauncher(String path, double yaxics, double xaxics){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        Parent root1 = null;
        try {
            root1 = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setY(yaxics);
        stage.setX(xaxics);

        stage.show();
    }

    private PageLauncher(Button btn, String path, double YPlus, double XPlus){

        Stage currentStage = (Stage) btn.getScene().getWindow();
        double yaxics = currentStage.getY()+YPlus;
        double xaxics = currentStage.getX()+XPlus;

        double middleY = currentStage.getY() + currentStage.getHeight() / 2;
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1.2), currentStage.getScene().getRoot());
        translateTransition.setToY(middleY);
        translateTransition.setOnFinished(event -> currentStage.close());
        translateTransition.play();

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1150);
                    Platform.runLater(() -> {
                        try {
                            PageLauncher.OpenPageWithLocation(path,yaxics,xaxics);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                }
            }
        }.start();
    }

    public static void LauncherPage(String path , Button btn){
        new PageLauncher(path,btn);
    }

    public static void OpenPage(String path){
        new PageLauncher(path);
    }

    public static void OpenPageWithLocation(String path, double yaxics, double xaxics){
        new PageLauncher(path, yaxics, xaxics);
    }

    public static void OpenPageWithAnimation(Button btn, String path, double YPlus, double XPlus){
        new PageLauncher(btn, path, YPlus, XPlus);
    }

}
