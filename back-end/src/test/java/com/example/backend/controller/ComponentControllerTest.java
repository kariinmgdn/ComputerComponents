package com.example.backend.controller;

import com.example.backend.domain.Component;
import com.example.backend.domain.Status;
import com.example.backend.dto.AddComponentRequest;
import com.example.backend.service.ComponentService;
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
class ComponentControllerTest {

    @Mock
    ComponentService service;

    @InjectMocks
    ComponentController controller;


    private final AddComponentRequest addComponentRequest =
            new AddComponentRequest("Monitors", "24 collas", "Nomainīt uz jaunāku");
    private final Component newComponent = new Component(addComponentRequest.name(),
            addComponentRequest.parameters(), addComponentRequest.reason());

    @Test
    void testAddComponent() {

        Mockito.doAnswer(invocationOnMock -> newComponent).when(service).addComponent(addComponentRequest);
        Component result = controller.addComponent(addComponentRequest);

        Component expected = new Component(newComponent.getId(), "Monitors", "24 collas",
                "Nomainīt uz jaunāku", Status.CREATED,
                newComponent.getTime());
        Assertions.assertEquals(expected.toString(), result.toString());
    }

    @Test
    void testGetComponentList() {

        Component first = new Component(1L, "Monitors", "24 collas",
                addComponentRequest.reason(), newComponent.getStatus(), newComponent.getTime());
        Component second = new Component(2L, "Monitors", "24 collas",
                addComponentRequest.reason(), newComponent.getStatus(), newComponent.getTime());

        List<Component> result = new ArrayList<>();

        result.add(first);
        result.add(second);

        Mockito.doAnswer(invocation -> result).when(service).getComponentList();

        Assertions.assertEquals(2, controller.getComponentList().size());
    }

    @Test
    void testChangeStatus() {
        newComponent.setStatus(Status.APPROVED);

        Mockito.doAnswer(invocation -> newComponent).when(service).changeStatus(newComponent.getId(), "Apstiprināts");
        Component result = controller.changeStatus(newComponent.getId(), "Apstiprināts");

        Component expected = new Component(newComponent.getId(), "Monitors", "24 collas",
                "Nomainīt uz jaunāku", Status.APPROVED,
                newComponent.getTime());

        Assertions.assertEquals(expected.toString(), result.toString());
    }

    @Test
    void testDeleteComponent() {
        Component component = new Component(1L, newComponent.getName(), newComponent.getParameters(),
                newComponent.getReason(), newComponent.getStatus(), newComponent.getTime());

        List<Component> components = new ArrayList<>();
        components.add(newComponent);
        components.add(component);

        Mockito.doAnswer(invocation -> components.remove(component)).when(service).deleteComponent(1L);
        controller.deleteComponent(1L);

        Mockito.doAnswer(invocation -> components).when(service).getComponentList();

        Assertions.assertEquals(1, controller.getComponentList().size());
    }
}
