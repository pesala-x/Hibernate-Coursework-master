package lk.pesala_X.bookWorm.Controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pesala_X.bookWorm.bo.BOFactory;
import lk.pesala_X.bookWorm.bo.custom.AdminBO;
import lk.pesala_X.bookWorm.dto.AdminDTO;
import lk.pesala_X.bookWorm.entity.Admin;

import java.io.IOException;
import java.util.regex.Pattern;

public class AdminLoginFormController {


    @FXML
    private AnchorPane pane;
    @FXML
    private Button Loginbtn;

    @FXML
    private CheckBox checkBox;

    @FXML
    private TextField textField;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    private final AdminBO adminBO = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);

    public void initialize(){
        addDefaultAdmin();
    }

    @FXML
    void btnBackOnAction(MouseEvent event) {
        pane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/mainLogin-form.fxml"));
            pane.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        loadDashboard();
        /*if (validateAdmin()){
            searchAdmin();
        }*/
    }

    private void searchAdmin(){
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        try {
            Admin admin = adminBO.searchAdmin(username, password);
            if (admin == null){
                new Alert(Alert.AlertType.WARNING,"Incorrect username or password").show();
            } else {
                loadDashboard();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING,"Incorrect username or password").show();
        }
    }

    private void loadDashboard(){
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/admin/adminDashboard-form.fxml"));
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin Dashboard");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.WARNING,e.getMessage()).show();
        }
    }

    private boolean validateAdmin(){
        String name = txtUsername.getText();
        boolean isFirstNameValidated = Pattern.compile("^[A-Za-z]{1,20}$").matcher(name).matches();

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

    private void addDefaultAdmin(){
        AdminDTO adminDTO = new AdminDTO("Pesala","1234");
        try {
            adminBO.saveAdmin(adminDTO);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
