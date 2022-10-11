package com.springbootapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DataTest {
@Test
public void testDataTestClass() {
    Data data = Mockito.spy(new Data());
    data.getEmail();
    data.setEmail("ashsy009@gmail.com");
    assertEquals(data.getEmail(),"ashsy009@gmail.com");

    data.getAvatar();
    data.setAvatar("image");
    assertEquals(data.getAvatar(),"image");

    data.getFirst_name();
    data.setFirst_name("David");
    assertEquals(data.getFirst_name(),"David");

    data.getId();
    data.setId("245");
    assertEquals(data.getId(), "245");

    data.getLast_name();
    data.setLast_name("Holt");
    assertEquals(data.getLast_name(), "Holt");

}
    
}
