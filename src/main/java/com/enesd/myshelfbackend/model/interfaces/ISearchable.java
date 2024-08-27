package com.enesd.myshelfbackend.model.interfaces;

import java.util.List;

public interface ISearchable {
    List<Object> searchAllRelevant(String searchTerm);
}
