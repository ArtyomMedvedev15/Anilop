package com.hobbiesservice.service.implementation;

import com.hobbiesservice.domain.Hobby;
import com.hobbiesservice.domain.Type;
import com.hobbiesservice.repository.HobbyRepository;
import com.hobbiesservice.service.api.HobbyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class HobbyServiceImpl implements HobbyService {

    private final HobbyRepository hobbyRepository;

    @Override
    public Hobby createHobby(Hobby hobbyCreate) {
        log.info("Create hobby - {}",hobbyCreate);
        return hobbyRepository.save(hobbyCreate);
    }

    @Override
    public Hobby updateHobby(Hobby hobbyUpdate) {
        log.info("Update hobby with id - {}",hobbyUpdate.getId());
        return hobbyRepository.save(hobbyUpdate);
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
    public List<Hobby> getAllHobbies() {
        log.info("Get all hobbies");
        return hobbyRepository.findAll();
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
}
