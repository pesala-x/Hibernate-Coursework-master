package lk.pesala_X.bookWorm.embeddad;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BookTransactionPK implements Serializable {
    private int bookId;
    private int transactionId;
}
