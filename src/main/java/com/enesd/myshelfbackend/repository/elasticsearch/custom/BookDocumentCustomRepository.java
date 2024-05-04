package com.enesd.myshelfbackend.repository.elasticsearch.custom;

import java.util.List;

public interface BookDocumentCustomRepository {
    public List<Integer> findAllIds();

}
