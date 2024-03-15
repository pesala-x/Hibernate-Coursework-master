package lk.pesala_X.bookWorm.bo.custom;

import javafx.collections.ObservableList;
import lk.pesala_X.bookWorm.bo.SuperBO;
import lk.pesala_X.bookWorm.dto.TransactionDTO;

public interface TransactionBO extends SuperBO {

    boolean saveTransaction(TransactionDTO dto) throws Exception;

    boolean updateTransaction(String id,TransactionDTO dto) throws Exception;

    boolean deleteTransaction(String id) throws Exception;

    TransactionDTO searchTransaction(String id) throws Exception;

    ObservableList<TransactionDTO> getAllTransaction() throws Exception;

    ObservableList<TransactionDTO> getUserTransaction(String user, String status) throws Exception;

    boolean updateStatus(int id,String status) throws Exception;

    ObservableList<TransactionDTO> getBranchTransaction(String branch) throws Exception;

}
