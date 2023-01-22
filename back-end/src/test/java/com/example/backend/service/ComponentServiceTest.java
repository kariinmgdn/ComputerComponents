package com.example.backend.service;

import com.example.backend.domain.Component;
import com.example.backend.dto.AddComponentRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ComponentServiceTest {

    @Mock
    ComponentService service;

    @Test
    void testClear() {
        AddComponentRequest addComponentRequest1 =
                new AddComponentRequest("monitors", "24 collas", "darbam");
        AddComponentRequest addComponentRequest2 =
                new AddComponentRequest("klaviatūra", "mehāniskā", "darbam");

        Component component1 = new Component(addComponentRequest1.name(), addComponentRequest1.parameters(),
                addComponentRequest1.reason());

        Component component2 = new Component(addComponentRequest2.name(), addComponentRequest2.parameters(),
                addComponentRequest2.reason());

        Mockito.doAnswer(invocation -> component1).when(service).addComponent(addComponentRequest1);
        Mockito.doAnswer(invocation -> component2).when(service).addComponent(addComponentRequest2);

        List<Component> components = new ArrayList<>();
        components.add(service.addComponent(addComponentRequest1));
        components.add(service.addComponent(addComponentRequest2));

        Mockito.doAnswer(invocation -> components.remove(component1)
                        && components.remove(component2))
                .when(service).clear();

        service.clear();

        Mockito.doAnswer(invocation -> components).when(service).getComponentList();

        Assertions.assertEquals(0, service.getComponentList().size());
    }

}
