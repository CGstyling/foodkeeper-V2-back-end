package com.example.demo.service;

import com.example.demo.FoodkeeperApplication;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes={FoodkeeperApplication.class})
public class UserServiceIntegrationTest {


    @InjectMocks
    private UserService userService;

    @Mock
    User user;

    @Mock
    private UserRepository userRepository;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Test
    public void testGetUserById() {
        //ARRANGE
        user = new User();
        user.setUserId(1L);

        //ACT
        Mockito.when(userRepository.existsById(1L)).thenReturn(true);
        Long expectedUserId = 1L;

        //ASSERT
        userService.getUserById(1L);
        assertEquals(expectedUserId, user.getUserId());
    }
}

