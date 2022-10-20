package com.hobbiesservice.service.api;

import com.hobbiesservice.domain.Hobby;
import com.hobbiesservice.domain.Type;

import java.util.List;

public interface HobbyService {
    Hobby createHobby(Hobby hobbyCreate);
    Hobby updateHobby(Hobby hobbyUpdate);
    void deleteHobby(Hobby hobbyDelete);
    Hobby getById(Long idHobby);
    List<Hobby>getAllHobbies();
    List<Hobby>findByName(String name);
    List<Hobby>findByType(Type type);
    List<Hobby>findByAuthor(Long idAuthor);

}
