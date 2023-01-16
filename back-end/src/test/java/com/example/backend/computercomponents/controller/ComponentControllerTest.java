package com.example.backend.computercomponents.controller;

import com.example.backend.computercomponents.dto.Component;
import com.example.backend.computercomponents.service.ComponentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ComponentControllerTest {

    @Mock
    ComponentService service;

    @InjectMocks
    ComponentController controller;


    private final Component component =
            new Component("Monitors", "24 collas", "Nomainīt uz jaunāku");

    @Test
    void testAddComponent() {
        Component newComponent = component;
        Mockito.doAnswer(invocationOnMock -> newComponent).when(service).addComponent(component);
        Component result = controller.addComponent(component);

        Component expected = new Component(newComponent.getId(), "Monitors", "24 collas",
                "Nomainīt uz jaunāku", "Izveidots",
                newComponent.getTime());
        Assertions.assertEquals(expected.toString(), result.toString());
    }

    @Test
    void testGetComponentList() {
        Component first = new Component(1L, "Monitors", "24 collas",
                component.getReason(), component.getStatus(), component.getTime());
        Component second = new Component(2L, "Monitors", "24 collas",
                component.getReason(), component.getStatus(), component.getTime());

        List<Component> result = new ArrayList<>();

        result.add(first);
        result.add(second);

        Mockito.doAnswer(invocation -> result).when(service).getComponentList();

        Assertions.assertEquals(2, controller.getComponentList().size());
    }

    @Test
    void testChangeStatus() {
        Component newComponent = component;
        newComponent.setStatus("Apstiprināts");

        Mockito.doAnswer(invocation -> newComponent).when(service).changeStatus(component.getId(), "Apstiprināts");
        Component result = controller.changeStatus(component.getId(), "Apstiprināts");

        Component expected = new Component(newComponent.getId(), "Monitors", "24 collas",
                "Nomainīt uz jaunāku", "Apstiprināts",
                newComponent.getTime());

        Assertions.assertEquals(expected.toString(), result.toString());
    }

    @Test
    void testDeleteComponent() {
        Component newComponent = new Component(1L, "Monitors", "24 collas",
                component.getReason(), component.getStatus(), component.getTime());
        List<Component> components = new ArrayList<>();
        components.add(component);
        components.add(newComponent);

        Mockito.doAnswer(invocation -> components.remove(newComponent)).when(service).deleteComponent(1L);
        controller.deleteComponent(1L);

        Mockito.doAnswer(invocation -> components).when(service).getComponentList();

        Assertions.assertEquals(1, controller.getComponentList().size());
    }
}
