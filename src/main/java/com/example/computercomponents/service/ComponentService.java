package com.example.computercomponents.service;

import com.example.computercomponents.dto.Component;
import com.example.computercomponents.repository.ComponentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ComponentService {

    private final ComponentRepository repository;

    public ComponentService(ComponentRepository repository) {
        this.repository = repository;
    }

    public Component addComponent(Component component) {
        return repository.save(new Component(component.getName(), component.getParameters(), component.getReason()));
    }

    public List<Component> getComponentList() {
        return repository.findAll();
    }

    public Component changeStatus(long id, String status) {
        Component component = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        component.setStatus(status);
        return repository.save(component);
    }

    public void deleteComponent(long id) {
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
    }

    public void clear() {
        repository.deleteAll();
    }
}
