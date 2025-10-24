package org.skypro.skyshop.search;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class SearchResult {
    private final UUID id;
    private final String name;
    private final String contentType;

    public SearchResult(UUID id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getContentType() {
        return contentType;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(searchable.getId(), searchable.getName(), searchable.getContentType());
    }
}
