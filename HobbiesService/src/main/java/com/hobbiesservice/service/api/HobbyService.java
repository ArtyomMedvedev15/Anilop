package com.hobbiesservice.service.api;

import com.hobbiesservice.domain.Hobby;

import java.util.List;

public interface HobbyService {
    Hobby createHobby(Hobby hobbyCreate);
    Hobby updateHobby(Hobby hobbyUpdate);
    void deleteHobby(Hobby hobbyDelete);
    Hobby getById(Long idHobby);
    List<Hobby>getAllHobbies();
    List<Hobby>findByName();
    List<Hobby>findByType();
    List<Hobby>findByAuthor();

}
