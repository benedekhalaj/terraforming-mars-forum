package com.codecool.terraformingmarsforum.model;

import io.vavr.control.Either;
import io.vavr.control.Validation;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * In this class we can validate any user related data
 */
@NoArgsConstructor
@Data
@Component
public class DataValidation {

    /**
     * This method validate username
     * If you need this method, you can use with built-in isLeft() and isRight() methods:
     * e.g.:
     * dataValidation.checkValidUsername(username).isLeft() == false
     * dataValidation.checkValidUsername(username).isRight() == true
     *
     * @param username which the user tries to log in (e.g)
     * @return Either.left as a failure or Either.right as a success depends on the criteria below
     */
    public Either<Class<IllegalArgumentException>, String> checkValidUsername(String username){
        if (username.length() < 1 || username.length() > 32){
            return Either.left(IllegalArgumentException.class);
        } else {
            return Either.right(username);
        }
    }

    //TODO email validation with built-in Validation?
    public Validation<String, String> checkValidEmail(String email){
        return Validation.valid(email);
    }
}
