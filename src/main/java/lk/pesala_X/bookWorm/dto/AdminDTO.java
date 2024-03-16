package lk.pesala_X.bookWorm.dto;

import lk.pesala_X.bookWorm.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AdminDTO {
    private int id;
    private String name;
    private String password;

    public AdminDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Admin toEntity(){
        return new Admin(id,name,password);
    }
}
