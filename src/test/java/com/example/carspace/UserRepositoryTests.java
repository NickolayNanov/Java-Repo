package com.example.carspace;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.carspace.authentication.User;
import com.example.carspace.repositories.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepositoryImpl repo;

    @Test
    public void testUserCreation() {
        User user = new User();

        user.setEmail("nickolay@nanov.bg");
        user.setFirstName("Nikolayyy");
        user.setLastName("Nanovvv");
        user.setPassword("123qwe");

        User created = repo.save(user);
        User found = entityManager.find(User.class, created.getId());

        assertThat(created.getEmail()).isEqualTo(found.getEmail());
    }
}