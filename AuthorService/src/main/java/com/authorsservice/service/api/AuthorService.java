package com.authorsservice.service.api;

import com.authorsservice.domain.Author;

import java.util.List;

public interface AuthorService {
    Author createAuthor(Author authorCreate);
    Author updateAuthor(Author authorUpdate);
    void deleteAuthor(Author authorDelete);
    Author getAuthorById(Long idAuthor);
    List<Author>getAllAuthors();
    List<Author>findAuthorByName(String nameAuthor);
}
