package lk.pesala_X.bookWorm.entity;

import jakarta.persistence.*;
import lk.pesala_X.bookWorm.embeddad.BookTransactionPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "bookTransactions_details")
public class BookTransaction {

    @EmbeddedId
    private BookTransactionPK bookTransactionPK;

    @ManyToOne
    @JoinColumn(name = "bookId" ,insertable = false,updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "transactionId",insertable = false,updatable = false)
    private Transactions transactions;

    @CreationTimestamp
    private Timestamp timestamp;

}
