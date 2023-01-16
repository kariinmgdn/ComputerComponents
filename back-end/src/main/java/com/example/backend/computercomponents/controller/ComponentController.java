package com.example.backend.computercomponents.controller;

import com.example.backend.computercomponents.dto.Component;
import com.example.backend.computercomponents.service.ComponentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
public class ComponentController {

    private final ComponentService service;

    public ComponentController(ComponentService service) {
        this.service = service;
    }

    @PostMapping("/")
    public Component addComponent(@RequestBody @Valid Component component) {
        return service.addComponent(component);
    }

    @GetMapping("/")
    public List<Component> getComponentList() {
        return service.getComponentList();
    }

    @PutMapping("/{id}")
    public Component changeStatus(@PathVariable long id, @RequestBody String status) {
        return service.changeStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteComponent(@PathVariable long id) {
        service.deleteComponent(id);
    }
}
