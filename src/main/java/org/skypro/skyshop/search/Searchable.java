package org.skypro.skyshop.search;

import java.util.UUID;

public interface Searchable {
    String searchTerm();

    String getContentType();

    String getName();

    UUID getId();

    default String getStringRepresentation() {
        return getName() + " " + getContentType();
    }
}
