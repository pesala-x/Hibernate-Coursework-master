package lk.pesala_X.bookWorm.Controller.User;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.pesala_X.bookWorm.bo.BOFactory;
import lk.pesala_X.bookWorm.bo.custom.BookBO;
import lk.pesala_X.bookWorm.bo.custom.TransactionBO;
import lk.pesala_X.bookWorm.dto.TransactionDTO;

import java.util.Optional;

public class BookBorrowFormController {

    @FXML
    private TableColumn<?, ?> colBorrow;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colReturn;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colUser;

    @FXML
    private TableView<TransactionDTO> tblBooks;


    private final TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSACTION);

    private final BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    public void initialize(){
        returnBook();
        reload();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colBorrow.setCellValueFactory(new PropertyValueFactory<>("borrowing"));
        colReturn.setCellValueFactory(new PropertyValueFactory<>("returning"));
    }

    private void returnBook(){
        try {
            tblBooks.setOnMouseClicked(event -> {
                TransactionDTO selectedItem = tblBooks.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure want to return this Book?", ok, no).showAndWait();
                    if (result.orElse(no) == ok) {
                        updateBookStatus(selectedItem.getId());
                        updateTransactionStatus(selectedItem.getId());
                        reload();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTransactionStatus(int id){
        try {
            String status = "Return";
            transactionBO.updateStatus(id,status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBookStatus(int id){
        try {
            String status = "Available";
            bookBO.updateStatus(id,status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reload() {
        loadAllTransactions();
        setCellValueFactory();
    }

    private void loadAllTransactions(){
        try {
            String status = "Borrow";
            tblBooks.setItems(transactionBO.getUserTransaction(UserLoginFormController.member,status));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
