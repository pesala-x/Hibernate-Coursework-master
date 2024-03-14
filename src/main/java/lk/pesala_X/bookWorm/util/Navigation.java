package lk.pesala_X.bookWorm.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Navigation {
    private static Parent rootNode;
    private static Scene scene;
    private static Stage stage;

    public static void switchNavigation(String path, ActionEvent event) throws IOException {
        rootNode= FXMLLoader.load(Navigation.class.getResource(path));

        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();

        scene =new Scene(rootNode);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public static void switchPaging(Pane pane, String path) throws IOException {
        pane.getChildren().clear();
        FXMLLoader loader=new FXMLLoader(Navigation.class.getResource(path));

        Parent root=loader.load();
        pane.getChildren().add(root);

    }

    public static void popupPaging(Pane pane,String path) throws IOException {
        FXMLLoader loader=new FXMLLoader(Navigation.class.getResource(path));
        Parent root=loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(pane.getScene().getWindow());

        popupStage.initStyle(StageStyle.UNDECORATED);

        Scene scene1=new Scene(root);
        popupStage.setScene(scene1);
        Navigation.setPopupStage(popupStage);

        popupStage.centerOnScreen();

        popupStage.showAndWait();
    }
    private static Stage popupStage;

    private static void setPopupStage(Stage stage) {
        popupStage=stage;

    }


    public static void closePopup() {
        if (popupStage != null) {
            popupStage.close();
            popupStage = null; // Set to null to avoid potential memory leaks
        }
    }

    public static void closeCurrentWindow(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

}
