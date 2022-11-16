package co.com.memoodm.tenpo.service.user.service;

import co.com.memoodm.tenpo.service.user.entity.UserEntity;
import co.com.memoodm.tenpo.service.user.exceptions.EmailDuplicatedException;
import co.com.memoodm.tenpo.service.user.exceptions.UserEmailDontExistException;
import co.com.memoodm.tenpo.service.user.repository.UserRepository;
import co.com.memoodm.tenpo.service.user.web.client.JWTResponse;
import co.com.memoodm.tenpo.service.user.web.client.LogInRequest;
import co.com.memoodm.tenpo.service.user.web.client.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest
{

    @Mock
    private UserRepository repository;

    @Mock
    private CredentialsService credentialsService;

    private UserService userService;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(repository,credentialsService);

        Mockito.when(repository.findByEmail( "email@email" ))
                .thenReturn(Optional.ofNullable(UserEntity.builder().email("email@email").build()));

        Mockito.when(repository.save( any() ))
                .thenReturn(UserEntity.builder().id(1L).email("email@email").build());

        Mockito.when(credentialsService.generateHashPasword( anyString() ))
                .thenReturn("HASH");

        Mockito.doNothing().when(credentialsService).validatePassword( anyString(), anyString() );

        Mockito.when(credentialsService.generateJsonWebToken( any() ))
                .thenReturn("JWT-TOKEN");

    }

    @Test
    public void shouldCreateUser()
    {
        User user = userService.createUser(User.builder().email("non-existence@email").build());
        assertTrue( user.getId() == 1L );
    }

    @Test
    public void shouldFailCreateUser()
    {
        Exception exception = assertThrows(EmailDuplicatedException.class, () -> {
            userService.createUser(User.builder().email("email@email").build());
        });
        assertTrue( exception instanceof EmailDuplicatedException );
    }

    @Test
    public void shouldGenerateJWT()
    {
        JWTResponse response = userService.logIn( LogInRequest.builder().email("email@email").build() );
        assertTrue( response != null );
        assertEquals( response.getPayload() , "JWT-TOKEN");
    }

    @Test
    public void shouldFailGenerateJWT()
    {
        Exception exception = assertThrows(UserEmailDontExistException.class, () -> {
            userService.logIn(LogInRequest.builder().email("non-existence@email").build());
        });
        assertTrue( exception instanceof UserEmailDontExistException);
    }

}
