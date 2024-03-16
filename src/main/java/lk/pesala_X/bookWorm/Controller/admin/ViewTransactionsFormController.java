package lk.pesala_X.bookWorm.Controller.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.pesala_X.bookWorm.bo.BOFactory;
import lk.pesala_X.bookWorm.bo.custom.TransactionBO;
import lk.pesala_X.bookWorm.dto.TransactionDTO;

public class ViewTransactionsFormController {

    @FXML
    private TableColumn<?, ?> colBook;

    @FXML
    private TableColumn<?, ?> colBorrow;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colReturn;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colUser;

    @FXML
    private Label lblTitle;

    @FXML
    private TableView<TransactionDTO> tblTransaction;

    private final TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSACTION);

    public void initialize(){
        lblTitle.setText(AdminDashboardForm.branchName+" Branch Transactions");
        loadAllTransactions();
        setCellValueFactory();
    }

    @FXML
    private void btnNotReturnOnAction() {
        try {
            tblTransaction.setItems(transactionBO.getAllTransaction());
            colId.setText("Branch");
            setCellValueFactory();
            colId.setCellValueFactory(new PropertyValueFactory<>("branch"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBook.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colBorrow.setCellValueFactory(new PropertyValueFactory<>("borrowing"));
        colReturn.setCellValueFactory(new PropertyValueFactory<>("returning"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadAllTransactions(){
        try {
            tblTransaction.setItems(transactionBO.getBranchTransaction(AdminDashboardForm.branchName));
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
