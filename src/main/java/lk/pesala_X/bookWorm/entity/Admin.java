package lk.pesala_X.bookWorm.entity;

import jakarta.persistence.*;
import lk.pesala_X.bookWorm.dto.AdminDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "admin")
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int id;

    private String name;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "admin")
    private List<Branch> branches = new ArrayList<>();

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Admin(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public AdminDTO toDto(){
        return new AdminDTO(id,name,password);
    }

}
