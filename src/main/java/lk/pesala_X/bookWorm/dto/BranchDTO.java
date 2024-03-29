package lk.pesala_X.bookWorm.dto;

import lk.pesala_X.bookWorm.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BranchDTO {
    private int id;
    private String name;
    private String manager;
    private String address;
    private String email;

    public BranchDTO(String name, String manager, String address, String email) {
        this.name = name;
        this.manager = manager;
        this.address = address;
        this.email = email;
    }

    public Branch toEntity(){
        return new Branch(id, name, manager, address, email);
    }
}
