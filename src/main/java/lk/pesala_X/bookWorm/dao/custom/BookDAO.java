package lk.pesala_X.bookWorm.dao.custom;

import javafx.collections.ObservableList;
import lk.pesala_X.bookWorm.dao.CrudDAO;
import lk.pesala_X.bookWorm.entity.Book;

public interface BookDAO extends CrudDAO<Book> {

    ObservableList<Book> getAllBooks(String branch);

    ObservableList<Book> searchBookName(String title);

    boolean updateStatus(int id,String status);
}
