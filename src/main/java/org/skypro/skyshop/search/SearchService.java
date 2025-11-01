package org.skypro.skyshop.search;

import org.skypro.skyshop.service.StorageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<Searchable> getSearchables() {
        Collection<Searchable> searchables = new ArrayList<>();
        searchables.addAll(storageService.getAllProducts());
        searchables.addAll(storageService.getAllArticles());
        return searchables;
    }

    public Collection<SearchResult> search(String query) {
        return getSearchables()
                .stream()
                .filter(i -> i.searchTerm().toLowerCase().contains(query.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}
