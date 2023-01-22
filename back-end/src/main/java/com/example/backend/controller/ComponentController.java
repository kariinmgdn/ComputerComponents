package com.example.backend.controller;

import com.example.backend.domain.Component;
import com.example.backend.dto.AddComponentRequest;
import com.example.backend.service.ComponentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
class ComponentController {

    private final ComponentService service;

    public ComponentController(ComponentService service) {
        this.service = service;
    }

    @PostMapping("/")
    public Component addComponent(@RequestBody @Valid AddComponentRequest addComponentRequest) {
        return service.addComponent(addComponentRequest);
    }

    @GetMapping("/")
    public List<Component> getComponentList() {
        return service.getComponentList();
    }

    @PutMapping("/{id}")
    public Component changeStatus(@PathVariable long id, @RequestBody @NotBlank String status) {
        return service.changeStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteComponent(@PathVariable long id) {
        service.deleteComponent(id);
    }
}
