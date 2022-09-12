package com.codecool.terraformingmarsforum.controller;

import com.codecool.terraformingmarsforum.model.DataValidation;
import com.codecool.terraformingmarsforum.service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AppUserControllerTest {

    @Mock
    private AppUserService appUserService;
    @Mock
    private DataValidation dataValidation;
    private AppUserController appUserController;

    @BeforeEach
    public void init(){
        //TODO move datavalidation to AppUserServiceController
        appUserController = new AppUserController(appUserService, dataValidation);
    }

    @Test
    public void getUserById_HasStatus200() {
        HttpStatus expected = HttpStatus.OK;
        HttpStatus actual = appUserController.getUserById(1L).getStatusCode();

        assertEquals(expected, actual);
    }

    @Test
    public void getAppUserByUsernameOrEmail_HasStatus200(){
        HttpStatus expected = HttpStatus.OK;
        HttpStatus actual = appUserController.getUserByUsernameOrEmail("Test").getStatusCode();

        assertEquals(expected, actual);
    }
}