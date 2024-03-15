package lk.pesala_X.bookWorm.Controller.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.pesala_X.bookWorm.bo.BOFactory;
import lk.pesala_X.bookWorm.bo.custom.UserBO;
import lk.pesala_X.bookWorm.dto.UserDTO;

import java.io.IOException;
import java.util.regex.Pattern;

public class UserSignupFormController {

    @FXML
    private AnchorPane pane;
    @FXML
    private CheckBox checkBox;

    @FXML
    private TextField textField;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnBackOnAction() {
        pane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/user/userLogin-form.fxml"));
            pane.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSignupOnAction(ActionEvent event) {
        if(validateUser()){
            String name = txtUsername.getText();
            String email = txtEmail.getText();
            String password = txtPassword.getText();

            UserDTO userDTO = new UserDTO(name, email, password);
            try {
                boolean isSaved = userBO.saveUser(userDTO);
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION, "User Registered Successfully").show();
                    btnBackOnAction();
                } else {
                    new Alert(Alert.AlertType.WARNING, "User Registration Failed").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validateUser(){
        String name = txtUsername.getText();
        boolean isFirstNameValidated = Pattern.compile("^[A-Za-z]{1,20}$").matcher(name).matches();

        if (!isFirstNameValidated) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid name").show();
            txtUsername.setStyle("-fx-border-color:#ff0000;");
            txtUsername.requestFocus();
            return false;
        }

        String email = txtEmail.getText();
        boolean isEmailValidated = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(email).matches();

        if (!isEmailValidated) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid email").show();
            txtEmail.setStyle("-fx-border-color:#ff0000;");
            txtEmail.requestFocus();
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
