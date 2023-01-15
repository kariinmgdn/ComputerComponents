package com.example.computercomponents.service;

import com.example.computercomponents.dto.Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ComponentServiceTest {

    @Autowired
    private ComponentService service;

    @BeforeEach
    void clear() {
        service.clear();
    }

    private final Component component =
            new Component("Monitors", "24 collas", "Nomainīt uz jaunāku");

    @Test
    void testAddComponent() {
        Component newComponent = service.addComponent(component);
        Component result = new Component(newComponent.getId(), "Monitors", "24 collas",
                "Nomainīt uz jaunāku", "Izveidots",
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
                "Nomainīt uz jaunāku", "Apstiprināts",
                newComponent.getTime());

        Assertions.assertEquals(result.toString(),
                service.changeStatus(newComponent.getId(), "Apstiprināts").toString());
    }

    @Test
    void testDeleteComponent() {
        Component newComponent = service.addComponent(component);
        service.addComponent(component);
        service.deleteComponent(newComponent.getId());

        Assertions.assertEquals(1, service.getComponentList().size());
    }
}
