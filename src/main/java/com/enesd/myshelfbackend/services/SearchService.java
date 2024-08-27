package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.model.interfaces.ISearchable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SearchService {
    private final List<ISearchable> searchables;

    public Map<String, List<Object>> searchAllTypes(String searchTerm) {
        Map<String, List<Object>> results = new HashMap<>();

        for (ISearchable searchable : searchables) {
            List<Object> result = searchable.searchAllRelevant(searchTerm);
            results.put(searchable.getClass().getSimpleName(), result);
        }

        return results;
    }
}
