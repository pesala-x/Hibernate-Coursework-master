package lk.pesala_X.bookWorm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookDTO {
    private int id;
    private String branch;
    private String title;
    private String author;
    private String genre;
    private String status;
    private Timestamp dateTime;

    public BookDTO(String branch, String title, String author, String genre, String status) {
        this.branch = branch;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
    }
}
