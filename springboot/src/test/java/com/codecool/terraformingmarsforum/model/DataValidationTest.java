package com.codecool.terraformingmarsforum.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataValidationTest {

    private static DataValidation dataValidation;

    @BeforeAll
    public static void init(){
        dataValidation = new DataValidation();
    }

    @Test
    public void checkValidUsername_ValidUsername_ReturnsTrue(){
        boolean actual = dataValidation.checkValidUsername("TestUser").isRight();
        assertTrue(actual);
    }

    @Test
    public void checkValidUsername_EmptyStringAsUsername_ReturnsTrue(){
        boolean actual = dataValidation.checkValidUsername("").isLeft();
        assertTrue(actual);
    }

    @Test
    public void checkValidUsername_EmptyStringAsUsername_ReturnsFalse(){
        boolean actual = dataValidation.checkValidUsername("").isRight();
        assertFalse(actual);
    }

    @Test
    public void checkValidUsername_ValidUsername_ReturnsFalse(){
        boolean actual = dataValidation.checkValidUsername("TestUser").isLeft();
        assertFalse(actual);
    }

    @Test
    public void checkValidUsername_TooLongUsername_ReturnsFalse(){
        boolean actual = dataValidation.checkValidUsername("This is a too long string for a username, the rest of it is unnecessary").isRight();
        assertFalse(actual);
    }

    @Test
    public void checkValidUsername_TooLongUsername_ReturnsTrue(){
        boolean actual = dataValidation.checkValidUsername("This is a too long string for a username, the rest of it is unnecessary").isLeft();
        assertTrue(actual);
    }
}