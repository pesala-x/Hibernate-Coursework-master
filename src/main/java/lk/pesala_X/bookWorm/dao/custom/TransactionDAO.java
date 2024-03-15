package lk.pesala_X.bookWorm.dao.custom;

import javafx.collections.ObservableList;
import lk.pesala_X.bookWorm.dao.CrudDAO;
import lk.pesala_X.bookWorm.entity.Transactions;

public interface TransactionDAO extends CrudDAO<Transactions> {

    ObservableList<Transactions> getUserTransaction(String user, String status);

    ObservableList<Transactions> getBranchTransaction(String branch);

    boolean updateStatus(int id,String status);
}