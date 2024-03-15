package lk.pesala_X.bookWorm.bo.custom;

import javafx.collections.ObservableList;
import lk.pesala_X.bookWorm.bo.SuperBO;
import lk.pesala_X.bookWorm.dto.BookDTO;

public interface BookBO  extends SuperBO {
    boolean saveBook(BookDTO dto) throws Exception;

    boolean updateBook(String id,BookDTO dto) throws Exception;

    boolean deleteBook(String id) throws Exception;

    BookDTO searchBook(String id) throws Exception;

    ObservableList<BookDTO> getAllBooks(String branch) throws Exception;

    ObservableList<BookDTO> loadAllBooks() throws Exception;

    ObservableList<BookDTO> SearchBookName(String name) throws Exception;

    boolean updateStatus(int id,String status) throws Exception;

}
