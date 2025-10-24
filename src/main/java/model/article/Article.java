package model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String articleName;
    private final String articleText;
    private final UUID id;

    public Article(UUID id, String articleName, String articleText) {
        this.articleName = articleName;
        this.articleText = articleText;
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getArticleText() {
        return articleText;
    }

    @Override
    public String toString() {
        return articleName + "\n" + articleText;
    }

    @Override
    @JsonIgnore
    public String searchTerm() {
        return toString();
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return articleName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleName, article.articleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleName);
    }
}
