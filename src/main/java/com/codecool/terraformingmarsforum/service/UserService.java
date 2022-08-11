package com.codecool.terraformingmarsforum.service;


import com.codecool.terraformingmarsforum.model.User;
import com.codecool.terraformingmarsforum.service.DAO.UserDAO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private UserDAO userDAO;

    public int createUser(User user) {
        return userDAO.createUser(user).getId();
    }
}
