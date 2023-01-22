package com.example.backend.service;

import com.example.backend.domain.Component;
import com.example.backend.domain.Status;
import com.example.backend.dto.AddComponentRequest;
import com.example.backend.repository.ComponentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ComponentService {

    private final ComponentRepository repository;

    public ComponentService(ComponentRepository repository) {
        this.repository = repository;
    }

    public Component addComponent(AddComponentRequest addComponentRequest) {
        return repository.save(new Component(addComponentRequest.name(),
                addComponentRequest.parameters(), addComponentRequest.reason()));
    }

    public List<Component> getComponentList() {
        List<Component> components = repository.findAll();
        components.sort(Comparator.comparingLong(Component::getId));
        return components;
    }

    public Component changeStatus(long id, String input) {

        Component component = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Status status = Arrays.stream(Status.values())
                .filter(status1 -> input.equals(status1.getNameInLatvian()))
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        component.setStatus(status);

        return repository.save(component);
    }

    public void deleteComponent(long id) {

        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
    }

    public void clear() {
        repository.deleteAll();
    }
}
