package com.enesd.myshelfbackend.model.abstracts;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
}
