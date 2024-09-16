package com.enesd.myshelfbackend.repository.elasticsearch;

import com.enesd.myshelfbackend.model.documents.UserDocument;
import com.enesd.myshelfbackend.repository.elasticsearch.custom.IUserDocumentRepositoryCustom;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface UserDocumentRepository extends ElasticsearchRepository<UserDocument, UUID>, IUserDocumentRepositoryCustom {
}
