package com.authorsservice.rest;

import com.authorsservice.domain.Author;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.authorsservice.service.api.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/author")
@Slf4j
public class AuthorRestController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity<List<Author>>getAllAuthors(){
        log.info("Get all authors with endpoint");
        return ResponseEntity.ok().body(authorService.getAllAuthors());
    }

}
