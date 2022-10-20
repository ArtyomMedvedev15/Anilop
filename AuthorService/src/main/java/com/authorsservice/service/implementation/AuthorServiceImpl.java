package com.authorsservice.service.implementation;

import com.authorsservice.domain.Author;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.authorsservice.repository.AuthorRepository;
import com.authorsservice.service.api.AuthorService;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author createAuthor(Author authorCreate) {
        log.info("Create author - {}",authorCreate);
        return authorRepository.save(authorCreate);
    }

    @Override
    public Author updateAuthor(Author authorUpdate) {
        log.info("Update author - {}",authorUpdate);
        return authorRepository.save(authorUpdate);
    }

    @Override
    public void deleteAuthor(Author authorDelete) {
        log.info("Delete author - {}",authorDelete);
        authorRepository.delete(authorDelete);
    }

    @Override
    public Author getAuthorById(Long idAuthor) {
        log.info("Get author by id - {}",idAuthor);
        return authorRepository.getById(idAuthor);
    }

    @Override
    public List<Author> getAllAuthors() {
        log.info("Get all authors");
        return authorRepository.findAll();
    }


    @Override
    public List<Author> findAuthorByName(String nameAuthor) {
        log.info("Find author with name contains - {}",nameAuthor);
        return authorRepository.findAll().stream().filter(
                o1->o1.getNameAuthor().contains(nameAuthor)).toList();
    }
}
