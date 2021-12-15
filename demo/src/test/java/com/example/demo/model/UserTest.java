package com.example.demo.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void testGetUsername(){
        //Arrange
        User user = new User();

        //Act
        user.setUsername("Christina");

        //Assert
        String expectedUserName = "Christina";
        String actualUsername = user.getUsername();
        assertThat(actualUsername).isEqualTo(expectedUserName);
    }
}
