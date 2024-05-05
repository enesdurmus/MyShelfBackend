package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.base.Book;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity extends Book {
}
