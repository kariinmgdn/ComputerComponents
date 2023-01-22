package com.example.backend.service;

import com.example.backend.domain.Component;
import com.example.backend.domain.Status;
import com.example.backend.dto.AddComponentRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComponentServiceApplicationTest {

    @Autowired
    private ComponentService service;

    @BeforeEach
    void clear() {
        service.clear();
    }

    private final AddComponentRequest component =
            new AddComponentRequest("Monitors", "24 collas", "Nomainīt uz jaunāku");

    @Test
    void testAddComponent() {
        Component newComponent = service.addComponent(component);
        Component result = new Component(newComponent.getId(), "Monitors", "24 collas",
                "Nomainīt uz jaunāku", Status.CREATED,
                newComponent.getTime());
        Assertions.assertEquals(result.toString(), newComponent.toString());
    }

    @Test
    void testGetComponentList() {

        service.addComponent(component);
        service.addComponent(component);

        Assertions.assertEquals(2, service.getComponentList().size());
    }

    @Test
    void testChangeStatus() {
        Component newComponent = service.addComponent(component);
        Component result = new Component(newComponent.getId(), "Monitors", "24 collas",
                "Nomainīt uz jaunāku", Status.APPROVED,
                newComponent.getTime());

        Assertions.assertEquals(result.getStatus(),
                service.changeStatus(newComponent.getId(), "Apstiprināts").getStatus());
    }

    @Test
    void testDeleteComponent() {
        Component newComponent = service.addComponent(component);
        service.addComponent(component);
        service.deleteComponent(newComponent.getId());

        Assertions.assertEquals(1, service.getComponentList().size());
    }
}
