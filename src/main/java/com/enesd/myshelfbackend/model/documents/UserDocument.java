package com.enesd.myshelfbackend.model.documents;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Data
@Document(indexName = "users")
public class UserDocument {
    @Id
    private UUID id;
    private String displayName;
}
