package com.springbootapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserTest {

    @Test
    public void getUserTest() {
    
       User user = Mockito.spy(new User()); 
   
       user.getData();
       user.setData(null);
       assertEquals(user.getData(), null);


    }
    
    
}
