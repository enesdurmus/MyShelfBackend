package com.enesd.myshelfbackend.model.documents;

import com.enesd.myshelfbackend.model.base.Book;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.Instant;

@Document(indexName = "books")
public class BookDocument extends Book {

    @Override
    public void setCreatedAt(Instant createdAt) {
    }

    @Override
    public void setUpdatedAt(Instant updatedAt) {
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
    }

    @Override
    public void setCreatedBy(String createdBy) {
    }
}
