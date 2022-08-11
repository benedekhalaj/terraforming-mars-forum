package com.codecool.terraformingmarsforum.service.DAO.memory;

import com.codecool.terraformingmarsforum.model.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserMemoryTest {

    @Test
    void create_user_presentInMemory() {
        Set<User> users = new HashSet<>();
        User user = User.builder().build();
        UserMemory memory = new UserMemory(users);
        memory.createUser(user);
        assertTrue(users.contains(user));
    }

}
