package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.base.Book;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class BookEntity extends Book {
}
