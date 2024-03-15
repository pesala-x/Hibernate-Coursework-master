package lk.pesala_X.bookWorm.Controller.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.pesala_X.bookWorm.bo.BOFactory;
import lk.pesala_X.bookWorm.bo.custom.BookBO;
import lk.pesala_X.bookWorm.bo.custom.TransactionBO;
import lk.pesala_X.bookWorm.dto.BookDTO;
import lk.pesala_X.bookWorm.dto.TransactionDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public class BookSearchFormController {

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colDateTime;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<BookDTO> tblBooks;

    @FXML
    private TextField txtSearch;


    private final BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    private final TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSACTION);

    public void initialize(){
        reload();
        borrowBook();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String title = txtSearch.getText();
        if (title != null) {
            try {
                tblBooks.setItems(bookBO.SearchBookName(title));
                setCellValueFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.WARNING,"Please Enter Book Name").show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
    }

    private void borrowBook(){
        try {
            tblBooks.setOnMouseClicked(event -> {
                BookDTO selectedItem = tblBooks.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure want to borrow this Book?", ok, no).showAndWait();
                    if (result.orElse(no) == ok) {
                        getBook(selectedItem.getTitle(), selectedItem.getBranch());
                        updateStatus(selectedItem.getId());
                        reload();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllBooks(){
        try {
            tblBooks.setItems(bookBO.loadAllBooks());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getBook(String title , String branch){
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate returnDate = currentDate.plusDays(30);
            String status = "Borrow";

            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setUserName(UserLoginFormController.member);
            transactionDTO.setBookTitle(title);
            transactionDTO.setBranch(branch);
            transactionDTO.setStatus(status);
            transactionDTO.setBorrowing(Date.valueOf(currentDate));
            transactionDTO.setReturning(Date.valueOf(returnDate));

            transactionBO.saveTransaction(transactionDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStatus(int id){
        try {
            String status = "Not Available";
            bookBO.updateStatus(id,status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reload(){
        loadAllBooks();
        setCellValueFactory();
    }
}
