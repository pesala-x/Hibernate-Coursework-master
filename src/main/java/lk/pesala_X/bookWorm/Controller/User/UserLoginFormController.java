package lk.pesala_X.bookWorm.Controller.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pesala_X.bookWorm.bo.BOFactory;
import lk.pesala_X.bookWorm.bo.custom.UserBO;
import lk.pesala_X.bookWorm.entity.User;

import java.io.IOException;
import java.util.regex.Pattern;

public class UserLoginFormController {

    @FXML
    private Button Loginbtn;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Hyperlink hypSignup;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField textField;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    public static String member;

    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    private void btnLoginOnAction() {
        if (validateAdmin()){
            searchUser();
        }
    }

    public void hypSignupOnAction(ActionEvent actionEvent) {
        pane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/user/userSignup-form.fxml"));
            pane.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnBackOnAction() {
        pane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/mainLogin-form.fxml"));
            pane.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchUser(){
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        try {
            User user = userBO.searchUser(username, password);
            if (user == null){
                new Alert(Alert.AlertType.WARNING,"Incorrect username or password").show();
            } else {
                member = username;
                loadDashboard();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING,"Incorrect username or password").show();
        }
    }

    private void loadDashboard(){
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/User/userDashboard-form.fxml"));
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("User Dashboard");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            //new Alert(Alert.AlertType.WARNING,e.getMessage()).show();
        }
    }

    private boolean validateAdmin(){
        String name = txtUsername.getText();
        boolean isFirstNameValidated = Pattern.compile("^[A-Za-z0-9\\s',.:-]+$").matcher(name).matches();

        if (!isFirstNameValidated) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid user name").show();
            txtUsername.setStyle("-fx-border-color:#ff0000;");
            txtUsername.requestFocus();
            return false;
        }

        String password = txtPassword.getText();
        boolean isPasswordValidated = Pattern.compile("^[A-Za-z0-9+_.-]{4,20}$").matcher(password).matches();

        if (!isPasswordValidated) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid password").show();
            txtPassword.setStyle("-fx-border-color:#ff0000;");
            txtPassword.requestFocus();
            return false;
        }
        return true;
    }
}
