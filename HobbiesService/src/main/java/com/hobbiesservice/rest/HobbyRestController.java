package com.hobbiesservice.rest;

import com.hobbiesservice.domain.Hobby;
import com.hobbiesservice.service.api.HobbyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hobby")
@RequiredArgsConstructor
@Slf4j
public class HobbyRestController {

    private final HobbyService hobbyService;

    @GetMapping("/hobbies")
    public ResponseEntity<List<Hobby>>getAll(){
        log.info("Get all hobbies with endpoint");
        return ResponseEntity.ok().body(hobbyService.getAllHobbies());
    }

}
