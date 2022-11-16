package co.com.memoodm.tenpo.service.user.repository;

import co.com.memoodm.tenpo.service.user.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest
{

    @Autowired
    private UserRepository repository;

    @Test
    public void shouldFindByEmailTest()
    {
        String email = "test@test.com";
        UserEntity user = UserEntity.builder().id(1L).email(email).build();
        repository.save( user );
        assertTrue( repository.findByEmail(email).isPresent() );
    }

    @Test
    public void shouldNotFindByEmailTest()
    {
        String email = "non-existentce@test.com";
        assertTrue( repository.findByEmail(email).isEmpty() );
    }

}
