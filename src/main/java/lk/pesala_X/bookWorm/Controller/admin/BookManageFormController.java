package lk.pesala_X.bookWorm.Controller.admin;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pesala_X.bookWorm.bo.BOFactory;
import lk.pesala_X.bookWorm.bo.custom.BookBO;
import lk.pesala_X.bookWorm.dto.BookDTO;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


public class BookManageFormController {
    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTimeStamp;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTitle;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<BookDTO> tblBook;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBranch;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtTitle;

    private final BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    private final String branchName = AdminDashboardForm.branchName;

    public void initialize(){
        //updateRealTime(lblTime);
        lblTitle.setText("Welcome To "+branchName+" Branch");
        txtBranch.setText(branchName);
        reload();
    }
    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colTimeStamp.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
    }

    @FXML
    private void btnClearOnAction() {
        resetBoarderColour();
        txtSearch.setText("");
        txtBranch.setText(branchName);
        txtTitle.setText("");
        txtAuthor.setText("");
        txtGenre.setText("");
        reload();
    }

    @FXML
    private void btnDeleteOnAction() {
        String id = txtSearch.getText();
        if (Pattern.compile("\\d+").matcher(id).matches()) {
            try {
                boolean isDeleted = bookBO.deleteBook(id);
                if (isDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION, "Book Delete Successfully").show();
                    btnClearOnAction();
                }  else {
                    new Alert(Alert.AlertType.ERROR, "Book not found").show();
                    txtSearch.setStyle("-fx-border-color:#ff0000;");
                    txtSearch.requestFocus();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Invalid book id").show();
                txtSearch.setStyle("-fx-border-color:#ff0000;");
                txtSearch.requestFocus();
            }
        }
    }

    @FXML
    private void btnSaveOnAction() {
        if (validateBook()){
            String branch = txtBranch.getText();
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String genre = txtGenre.getText();
            String status = "Available";

            BookDTO bookDTO = new BookDTO(branch,title,author,genre,status);
            try {
                boolean isSaved = bookBO.saveBook(bookDTO);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Book Added Successfully").show();
                    btnClearOnAction();
                    reload();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Book Added Failed").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btnSearchOnAction() {
        String id = txtSearch.getText();
        if (Pattern.compile("\\d+").matcher(id).matches()) {
            try {
                BookDTO bookDTO = bookBO.searchBook(id);
                if (bookDTO != null){
                    txtBranch.setText(bookDTO.getBranch());
                    txtTitle.setText(bookDTO.getTitle());
                    txtAuthor.setText(bookDTO.getAuthor());
                    txtGenre.setText(bookDTO.getGenre());
                }  else {
                    new Alert(Alert.AlertType.ERROR, "Please enter a valid book id").show();
                    txtSearch.setStyle("-fx-border-color:#ff0000;");
                    txtSearch.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btnUpdateOnAction() {
        String id = txtSearch.getText();

        if (Pattern.compile("\\d+").matcher(id).matches() && validateBook()) {
            String branch = txtBranch.getText();
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String genre = txtGenre.getText();
            String status = "Availble";

            BookDTO bookDTO = new BookDTO(branch,title,author,genre,status);
            try {
                boolean isUpdated = bookBO.saveBook(bookDTO);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Book Update Successfully").show();
                    btnClearOnAction();
                    reload();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Book Update Failed").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  else {
            new Alert(Alert.AlertType.WARNING, "Invalid Book ID").show();
            txtSearch.setStyle("-fx-border-color:#ff0000;");
            txtSearch.requestFocus();
        }
    }

    private void loadAllBooks(){
        try {
            tblBook.setItems(bookBO.getAllBooks(txtBranch.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateBook(){
        String title = txtTitle.getText();
        boolean isTitleValidated = Pattern.compile("^[A-Za-z]{1,20}$").matcher(title).matches();

        if (!isTitleValidated) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid book title").show();
            txtTitle.setStyle("-fx-border-color:#ff0000;");
            txtTitle.requestFocus();
            return false;
        }

        String author = txtAuthor.getText();
        boolean isAuthorValidated = Pattern.compile("^[A-Za-z]{1,20}$").matcher(author).matches();

        if (!isAuthorValidated) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid book author").show();
            txtAuthor.setStyle("-fx-border-color:#ff0000;");
            txtAuthor.requestFocus();
            return false;
        }

        String genre = txtGenre.getText();
        boolean isGenreValidated = Pattern.compile("^[A-Za-z]{1,20}$").matcher(genre).matches();

        if (!isGenreValidated) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid book genre").show();
            txtGenre.setStyle("-fx-border-color:#ff0000;");
            txtGenre.requestFocus();
            return false;
        }
        return true;
    }

    @FXML
    private void btnBackOnAction() {
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

    @FXML
    private void btnViewTransactionsOnAction() {
        try {
            URL resource = BookManageFormController.class.getResource("/view/admin/viewTransactionForm.fxml");
            Parent parent = FXMLLoader.load(resource);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Transaction Form");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reload(){
        loadAllBooks();
        setCellValueFactory();
    }

    private void resetBoarderColour(){
        txtSearch.setStyle("-fx-border-color: black");
        txtAuthor.setStyle("-fx-border-color: black");
        txtTitle.setStyle("-fx-border-color: black");
        txtGenre.setStyle("-fx-border-color: black");
    }

    private void updateRealTime(Label label) {
        lblDate.setText(LocalDate.now().toString());
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            String currentTime = LocalDateTime.now().format(timeFormatter);
            Platform.runLater(() -> label.setText(currentTime));
        }, 0, 1, TimeUnit.SECONDS);
    }
}
