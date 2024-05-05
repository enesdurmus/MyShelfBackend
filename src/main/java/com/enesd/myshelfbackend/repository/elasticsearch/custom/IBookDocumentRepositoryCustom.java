package com.enesd.myshelfbackend.repository.elasticsearch.custom;


import java.util.List;

public interface IBookDocumentRepositoryCustom {
    List<Integer> findAllIds();
}
