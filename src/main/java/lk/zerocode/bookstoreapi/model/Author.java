package lk.zerocode.bookstoreapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nic;
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> bookList;

    @OneToMany(mappedBy = "author")
    private List<LoyaltyPoint> points;
}
