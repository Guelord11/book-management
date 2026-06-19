package sn.iage.isi.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class Book extends BaseEntity {

    @Column(nullable = false, length = 20, unique = true)
    private String isbn;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 150)
    private String author;

    @Column(nullable = false, name = "publication_year")
    private int publicationYear;

    @Column(nullable = false, name = "count_pages")
    private int countPages;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}