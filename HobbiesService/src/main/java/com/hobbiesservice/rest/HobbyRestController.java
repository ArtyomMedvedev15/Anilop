package com.hobbiesservice.rest;

import com.hobbiesservice.domain.Hobby;
import com.hobbiesservice.dto.HobbyRequest;
import com.hobbiesservice.dto.HobbyResponse;
import com.hobbiesservice.service.api.HobbyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hobby")
@RequiredArgsConstructor
@Slf4j
public class HobbyRestController {

    private final HobbyService hobbyService;

    @GetMapping("/hobbies")
    @ResponseStatus(HttpStatus.OK)
    public List<HobbyResponse>getAll(){
        log.info("Get all hobbies with endpoint");
        return hobbyService.getAllHobbies();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createHobby(@RequestBody HobbyRequest hobbyRequest){
        log.info("Create hobby with endpoint");
        hobbyService.createHobby(hobbyRequest);
    }


}
