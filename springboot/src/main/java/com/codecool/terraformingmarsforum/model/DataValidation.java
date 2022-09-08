package com.codecool.terraformingmarsforum.model;

import io.vavr.control.Either;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DataValidation {

    public Either<String, String>  checkValidUsername(String username){
        if (username.length() < 1 || username.length() > 32){
            return Either.left("Username not valid!");
        } else {
            return Either.right(username);
        }
    }
}
