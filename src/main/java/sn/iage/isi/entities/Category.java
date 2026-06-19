package sn.iage.isi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "books")
@Builder
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")

public class Category extends BaseEntity {

        @Column(nullable = false, length = 100)
        private String name;
        private Boolean state;
}
