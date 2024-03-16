package lk.pesala_X.bookWorm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "transactions")
public class Transactions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int id;

    private String userName;

    @Column(name = "book_title")
    private String bookTitle;

    private String branch;

    private Date borrowing;

    private Date returning;

    private String status;

    @OneToMany(mappedBy = "transactions")
    private List<BookTransaction> bookTransactions = new ArrayList<>();

    public Transactions(String userName, String bookTitle, String branch,String status, Date borrowing, Date returning) {
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.branch = branch;
        this.status = status;
        this.borrowing = borrowing;
        this.returning = returning;
    }
}
