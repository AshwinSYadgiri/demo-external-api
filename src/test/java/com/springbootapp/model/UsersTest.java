package com.springbootapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UsersTest {

    @Test
    public void getUsersTest() {
    
       Users user = Mockito.spy(new Users()); 
       user.getPage();
       user.setPage(2);
       assertEquals(user.getPage(),2);

       user.getData();
       user.setData(null);
       assertEquals(user.getData(), null);

       user.getTotal();
       user.setTotal(5);
       assertEquals(user.getTotal(), 5);
 

    }
    
}
