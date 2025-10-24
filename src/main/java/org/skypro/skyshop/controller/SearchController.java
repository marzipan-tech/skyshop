package org.skypro.skyshop.controller;

import org.skypro.skyshop.search.SearchResult;
import org.skypro.skyshop.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class SearchController {
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }
}
