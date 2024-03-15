package lk.pesala_X.bookWorm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TransactionDTO {

    private int id;
    private String branch;
    private String bookTitle;
    private String userName;
    private Date borrowing;
    private Date returning;
    private String status;

}
