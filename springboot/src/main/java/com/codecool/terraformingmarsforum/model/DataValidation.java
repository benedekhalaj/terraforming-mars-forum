package com.codecool.terraformingmarsforum.model;

import io.vavr.control.Either;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * In this class we can validate any user related data
 */
@NoArgsConstructor
@Data
public class DataValidation {

    /**
     * This method validate username
     * If you need this method, you can use with built-in isLeft() and isRight() methods:
     * e.g.:
     * dataValidation.checkValidUsername(username).isLeft() == false
     *
     * dataValidation.checkValidUsername(username).isRight() == true
     *
     * @param username which the user tries to log in (e.g)
     * @return Either.left as a failure or Either.right as a success depends on the criteria below
     */
    public Either<String, String>  checkValidUsername(String username){
        if (username.length() < 1 || username.length() > 32){
            return Either.left("Username not valid!");
        } else {
            return Either.right(username);
        }
    }
}
