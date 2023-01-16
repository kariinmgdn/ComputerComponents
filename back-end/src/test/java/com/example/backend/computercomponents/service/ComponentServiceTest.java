package com.example.backend.computercomponents.service;

import com.example.backend.computercomponents.dto.Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ComponentServiceTest {

    @Mock
    ComponentService service;

    @Test
    void testClear() {
        Component component1 = new Component("monitors", "24 collas", "darbam");
        Component component2 = new Component("monitors", "24 collas", "darbam");

        Mockito.doAnswer(invocation -> component1).when(service).addComponent(component1);
        Mockito.doAnswer(invocation -> component2).when(service).addComponent(component2);

        List<Component> components = new ArrayList<>();
        components.add(service.addComponent(component1));
        components.add(service.addComponent(component2));

        Mockito.doAnswer(invocation -> components.remove(component1)
                        && components.remove(component2))
                .when(service).clear();

        service.clear();

        Mockito.doAnswer(invocation -> components).when(service).getComponentList();

        Assertions.assertEquals(0, service.getComponentList().size());
    }

}
