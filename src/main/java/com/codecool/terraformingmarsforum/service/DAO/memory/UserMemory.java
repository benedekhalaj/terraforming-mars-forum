package com.codecool.terraformingmarsforum.service.DAO.memory;

import com.codecool.terraformingmarsforum.model.User;
import com.codecool.terraformingmarsforum.service.DAO.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class UserMemory implements UserDAO {

    private final Set<User> users;
    private int idCounter;

    @Override
    public User createUser(User user) {
        users.add(user);
        user.setId(idCounter++);
        return user;
    }
}
