package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
//import static java.time.LocalDateTime.now;

@Data
@Entity
@Table(name="tblCategories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 150, nullable = false)
    private String name;
    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProductEntity> products;

    public CategoryEntity() {
    }

    public CategoryEntity(String name) {
        this.name = name;
//        this.dateCreated = now();
        this.dateCreated = LocalDateTime.now();
    }
}
