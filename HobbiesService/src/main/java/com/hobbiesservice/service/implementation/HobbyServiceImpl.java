package com.hobbiesservice.service.implementation;

import com.hobbiesservice.domain.Hobby;
import com.hobbiesservice.domain.Status;
import com.hobbiesservice.domain.Type;
import com.hobbiesservice.dto.HobbyRequest;
import com.hobbiesservice.dto.HobbyResponse;
import com.hobbiesservice.repository.HobbyRepository;
import com.hobbiesservice.service.api.HobbyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class HobbyServiceImpl implements HobbyService {

    private final HobbyRepository hobbyRepository;

    @Override
    public Hobby createHobby(HobbyRequest hobbyRequest) {
        Hobby hobby = getHobbyFromDto(hobbyRequest);
        hobby.setCreated(new Date());
        hobby.setStatus(Status.CREATED);
        log.info("Create hobby - {}",hobby.getName());
        return hobbyRepository.save(hobby);
    }



    @Override
    public Hobby updateHobby(HobbyRequest hobbyUpdate) {
         Hobby hobbyUpdated = getHobbyFromDto(hobbyUpdate);
         log.info("Update hobby with id - {}",hobbyUpdate.getId());
         hobbyUpdated.setUpdated(new Date());
        return hobbyRepository.save(hobbyUpdated);
    }

    @Override
    public void deleteHobby(Hobby hobbyDelete) {
        log.info("Delete hobby - {}",hobbyDelete);
        hobbyRepository.delete(hobbyDelete);
    }

    @Override
    public Hobby getById(Long idHobby) {
        log.info("Get hobby by id - {}",idHobby);
        return hobbyRepository.getById(idHobby);
    }

    @Override
    public List<HobbyResponse> getAllHobbies() {
        log.info("Get all hobbies");
        List<Hobby>hobbies = hobbyRepository.findAll();
        return hobbies.stream().map(this::mapToHobbyResponse).toList();
    }


    @Override
    public List<Hobby> findByName(String name) {
        log.info("Find all hobby witch contains name - {}",name);
        return hobbyRepository.findAll().stream().filter(o1->o1.getName().contains(name)).toList();
    }

    @Override
    public List<Hobby> findByType(Type type) {
        log.info("Find all hobby with type - {}", type);
        return hobbyRepository.findAll().stream().filter(o1->o1.getType().equals(type)).toList();
    }

    @Override
    public List<Hobby> findByAuthor(Long idAuthor) {
        log.info("Find all hobby with author id - {}",idAuthor);
        return hobbyRepository.findAll().stream().filter(o1->o1.getAuthor_id().equals(idAuthor)).toList();
    }
    private HobbyResponse mapToHobbyResponse(Hobby hobby) {
        return HobbyResponse.builder()
                .id(hobby.getId())
                .created(hobby.getCreated())
                .name(hobby.getName())
                .describe(hobby.getDescribe())
                .duration(hobby.getDuration())
                .rating(hobby.getRating())
                .logoPath(hobby.getLogoPath())
                .author_id(hobby.getAuthor_id())
                .type(hobby.getType())
                .created(hobby.getCreated())
                .build();
    }

    private static Hobby getHobbyFromDto(HobbyRequest hobbyRequest) {
        return Hobby.builder()
                .id(hobbyRequest.getId())
                .name(hobbyRequest.getName())
                .describe(hobbyRequest.getDescribe())
                .duration(hobbyRequest.getDuration())
                .logoPath(hobbyRequest.getLogoPath())
                .type(hobbyRequest.getType())
                .author_id(hobbyRequest.getAuthor_id())
                .rating(hobbyRequest.getRating())
                .build();
    }
}
