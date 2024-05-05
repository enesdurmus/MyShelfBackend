package com.enesd.myshelfbackend.model.documents;

import com.enesd.myshelfbackend.model.base.Book;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "books")
public class BookDocument extends Book {
}
