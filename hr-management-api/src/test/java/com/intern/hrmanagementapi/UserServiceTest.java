package com.intern.hrmanagementapi;

import com.intern.hrmanagementapi.entity.UserEntity;
import com.intern.hrmanagementapi.exception.ResourceNotFoundException;
import com.intern.hrmanagementapi.repo.UserRepo;
import com.intern.hrmanagementapi.service.UserService;
import com.intern.hrmanagementapi.type.UserRole;
import com.intern.hrmanagementapi.type.UserState;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserService userService;
    @Test
    void testGetUser() throws ResourceNotFoundException {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        UUID id = UUID.randomUUID();
        UserEntity user = new UserEntity(
                id,
                "leducanh",
                "leducanh@gmail.com",
                "454645",
                UserRole.USER,
                UserState.ACTIVE,
                null,
                new Date(),
                new Date(),
                null
                );
        Mockito.when(userRepo.findById(id)).thenReturn(Optional.of(user));
        Assert.assertEquals(id, userService.getUser(id.toString()));
    }
}
