package lk.pesala_X.bookWorm.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.pesala_X.bookWorm.bo.custom.TransactionBO;
import lk.pesala_X.bookWorm.dao.DAOFactory;
import lk.pesala_X.bookWorm.dao.custom.TransactionDAO;
import lk.pesala_X.bookWorm.dto.TransactionDTO;
import lk.pesala_X.bookWorm.entity.Transactions;

import java.util.ArrayList;
import java.util.List;

public class TransactionBOImpl implements TransactionBO {

    private final TransactionDAO transactionDAO = (TransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TRANSACTION);

    @Override
    public boolean saveTransaction(TransactionDTO dto) throws Exception {
        return transactionDAO.save(new Transactions(
                dto.getUserName(),
                dto.getBookTitle(),
                dto.getBranch(),
                dto.getStatus(),
                dto.getBorrowing(),
                dto.getReturning()
        ));
    }

    @Override
    public boolean updateTransaction(String id, TransactionDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteTransaction(String id) throws Exception {
        return false;
    }

    @Override
    public TransactionDTO searchTransaction(String id) throws Exception {
        return null;
    }

    @Override
    public ObservableList<TransactionDTO> getAllTransaction(){
        return null;
    }

    @Override
    public ObservableList<TransactionDTO> getUserTransaction(String user, String status) {
        List<Transactions> transactionList = transactionDAO.getUserTransaction(user,status);
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (Transactions transaction : transactionList) {
            transactionDTOS.add(new TransactionDTO(
                    transaction.getId(),
                    transaction.getBranch(),
                    transaction.getBookTitle(),
                    transaction.getUserName(),
                    transaction.getBorrowing(),
                    transaction.getReturning(),
                    transaction.getStatus()
            ));
        }
        return FXCollections.observableArrayList(transactionDTOS);
    }

    @Override
    public boolean updateStatus(int id, String status) throws Exception {
        return transactionDAO.updateStatus(id,status);
    }

    @Override
    public ObservableList<TransactionDTO> getBranchTransaction(String branch) {
        List<Transactions> transactionList = transactionDAO.getBranchTransaction(branch);
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (Transactions transaction : transactionList) {
            transactionDTOS.add(new TransactionDTO(
                    transaction.getId(),
                    transaction.getBranch(),
                    transaction.getBookTitle(),
                    transaction.getUserName(),
                    transaction.getBorrowing(),
                    transaction.getReturning(),
                    transaction.getStatus()
            ));
        }
        return FXCollections.observableArrayList(transactionDTOS);
    }
}
