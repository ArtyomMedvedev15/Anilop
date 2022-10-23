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

    @PostMapping("/update")
    public ResponseEntity<HobbyRequest> updateHobby(@RequestBody HobbyRequest hobbyRequest){
        log.info("Update hobby with endpoint");
        hobbyService.updateHobby(hobbyRequest);

        return ResponseEntity.ok().body(hobbyRequest);

    }

    @GetMapping("/{id}")
    public ResponseEntity<HobbyResponse> getHobbyById(@PathVariable("id")Long idHobby){
        log.info("Get hobby by id with endpoint");
        HobbyResponse hobbyResponse = hobbyService.getById(idHobby);
        return ResponseEntity.ok().body(hobbyResponse);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HobbyResponse>deleteHobbyById(@PathVariable("id")Long idHobby){
        HobbyResponse hobbyDelete = hobbyService.getById(idHobby);
        log.info("Delete hobby with id - {}",idHobby);
        hobbyService.deleteHobby(hobbyDelete);
        return ResponseEntity.noContent().build();
    }

}
