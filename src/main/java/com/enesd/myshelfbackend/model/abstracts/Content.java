package com.enesd.myshelfbackend.model.abstracts;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
