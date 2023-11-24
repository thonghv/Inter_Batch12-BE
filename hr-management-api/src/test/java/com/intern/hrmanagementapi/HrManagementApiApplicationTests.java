package com.intern.hrmanagementapi;

import com.intern.hrmanagementapi.entity.UserEntity;
import com.intern.hrmanagementapi.exception.ResourceNotFoundException;
import com.intern.hrmanagementapi.repo.UserRepo;
import com.intern.hrmanagementapi.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HrManagementApiApplicationTests {
	@Mock
	private UserRepo userRepo;
	@InjectMocks
	private UserService userService;
	@Test
	void testGetUser() throws ResourceNotFoundException {
		UUID id = UUID.randomUUID();
		UserEntity user = new UserEntity();
		user.setId(id);
		user.setEmail("leducanh454645@gmail.com");
		Mockito.when(userRepo.findById(id)).thenReturn(Optional.of(user));
		Assert.assertEquals(user, userService.getUser(String.valueOf(id)));
	}
}
