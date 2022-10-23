package com.hobbiesservice.service.api;

import com.hobbiesservice.domain.Hobby;
import com.hobbiesservice.domain.Type;
import com.hobbiesservice.dto.HobbyRequest;
import com.hobbiesservice.dto.HobbyResponse;

import java.util.List;

public interface HobbyService {
    Hobby createHobby(HobbyRequest hobbyCreate);
    Hobby updateHobby(HobbyRequest hobbyUpdate);
    void deleteHobby(HobbyResponse hobbyDelete);
    HobbyResponse getById(Long idHobby);
    List<HobbyResponse>getAllHobbies();
    List<Hobby>findByName(String name);
    List<Hobby>findByType(Type type);
    List<Hobby>findByAuthor(Long idAuthor);


}
