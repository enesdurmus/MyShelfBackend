package com.enesd.myshelfbackend.model.documents;

import com.enesd.myshelfbackend.model.base.Book;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@Document(indexName = "book_index")
public class BookDocument extends Book {

}
