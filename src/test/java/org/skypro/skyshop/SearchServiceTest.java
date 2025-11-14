package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.search.SearchResult;
import org.skypro.skyshop.search.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    public void givenNoProductsInStorage_WhenSearch_ThenReturnEmptyList() {
        when(storageService.getAllProducts()).thenReturn(Collections.emptyList());
        when(storageService.getAllArticles()).thenReturn(Collections.emptyList());
        Collection<SearchResult> results = searchService.search("query");
        assertTrue(results.isEmpty());
    }

    @Test
    public void givenNoSuchProductInStorage_WhenSearch_ThenReturnEmptyList() {
        Collection<Product> notThisProducts = Arrays.asList(
                new SimpleProduct(UUID.randomUUID(), "different product", 100),
                new DiscountedProduct(UUID.randomUUID(), "another different product", 100, 150)
        );
        Collection<Article> notThisArticles = Arrays.asList(
                new Article(UUID.randomUUID(), "different article", "some text"),
                new Article(UUID.randomUUID(), "another different article", "some text")
        );
        when(storageService.getAllProducts()).thenReturn(notThisProducts);
        when(storageService.getAllArticles()).thenReturn(notThisArticles);
        Collection<SearchResult> results = searchService.search("query");
        assertTrue(results.isEmpty());
    }

    @Test
    public void givenThereIsSuchProductInStorage_WhenSearch_ThenReturnListOfProducts() {
        Collection<Product> someProducts = Arrays.asList(
                new SimpleProduct(UUID.randomUUID(), "this product", 100),
                new DiscountedProduct(UUID.randomUUID(), "another product", 100, 150)
        );
        Collection<Article> someArticles = Arrays.asList(
                new Article(UUID.randomUUID(), "this article", "some text"),
                new Article(UUID.randomUUID(), "another article", "some text")
        );
        when(storageService.getAllProducts()).thenReturn(someProducts);
        when(storageService.getAllArticles()).thenReturn(someArticles);
        Collection<SearchResult> results = searchService.search("this");
        assertFalse(results.isEmpty());
    }

    @Test
    public void givenThereIsSuchProductInStorage_WhenSearch_ThenReturnListOfProperProducts() {
        Product thisProduct = new SimpleProduct(UUID.randomUUID(), "this product", 100);
        Article thisArticle = new Article(UUID.randomUUID(), "this article", "some text");
        Product anotherProduct = new DiscountedProduct(UUID.randomUUID(), "another product", 100, 150);
        Article anotherArticle = new Article(UUID.randomUUID(), "another article", "some text");
        Collection<Product> someProducts = Arrays.asList(thisProduct, anotherProduct);
        Collection<Article> someArticles = Arrays.asList(thisArticle, anotherArticle);
        when(storageService.getAllProducts()).thenReturn(someProducts);
        when(storageService.getAllArticles()).thenReturn(someArticles);
        Collection<SearchResult> results = searchService.search("this");
        assertThat(results).extracting(SearchResult::getId).contains(thisProduct.getId());
        assertThat(results).extracting(SearchResult::getId).doesNotContain(anotherProduct.getId());
        assertThat(results).extracting(SearchResult::getId).contains(thisArticle.getId());
        assertThat(results).extracting(SearchResult::getId).doesNotContain(anotherArticle.getId());
    }
}
