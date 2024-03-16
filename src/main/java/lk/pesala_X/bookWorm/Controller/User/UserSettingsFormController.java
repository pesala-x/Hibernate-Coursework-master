package lk.pesala_X.bookWorm.Controller.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.pesala_X.bookWorm.bo.BOFactory;
import lk.pesala_X.bookWorm.bo.custom.UserBO;
import lk.pesala_X.bookWorm.dto.UserDTO;

import java.util.regex.Pattern;

public class UserSettingsFormController {

    @FXML
    private Label lblUserId;

    @FXML
    private Label lblUserName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
    private int userId;

    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize(){
        try {
            String user = UserLoginFormController.member;
            userId = userBO.searchUserId(user).getId();
            lblUserName.setText("User Name : " + user);
            lblUserId.setText("User ID : " + userId);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnUpdateDetailsOnAction() {
        if (validateUser()){
            String name = txtUserName.getText();
            String email = txtEmail.getText();
            String password = txtPassword.getText();

            UserDTO userDTO = new UserDTO(name, email, password);
            try {
                boolean isUpdated = userBO.updateUser(String.valueOf(userId),userDTO);
                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION, "User Update Successfully").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "User Update Failed").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validateUser(){
        String name = txtUserName.getText();
        boolean isFirstNameValidated = Pattern.compile("^[A-Za-z0-9\\s',.:-]+$").matcher(name).matches();

        if (!isFirstNameValidated) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid name").show();
            txtUserName.setStyle("-fx-border-color:#ff0000;");
            txtUserName.requestFocus();
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
