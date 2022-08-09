package com.users;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {


    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        Users user = new Users();
        user.setEmail("silvestre.tivir@quidgest.co.mz");
        user.setPassword("123");
        user.setFirstName("Silvestre");
        user.setLastName("Tivir");

        Users savedUsers = repo.save(user);

        Users existUsers = entityManager.find(Users.class, savedUsers.getId());

        assertThat(user.getEmail()).isEqualTo(existUsers.getEmail());

    }
}
