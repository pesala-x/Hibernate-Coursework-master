package lk.pesala_X.bookWorm.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data

@Entity
@Table(name = "Branch")
public class Branch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private int id;

    private String name;

    private String manager;

    private String address;

    private String email;

    @ManyToOne
    @JoinColumn(name = "admin_id", insertable = false, updatable = false)
    private Admin admin;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "branch")
    private List<Book> books = new ArrayList<>();

    public Branch(String name, String manager, String address, String email) {
        this.name = name;
        this.manager = manager;
        this.address = address;
        this.email = email;
    }

}
