package co.com.memoodm.tenpo.service.user.service;

import co.com.memoodm.tenpo.service.user.web.client.JWTWebPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CredentialsServiceTest extends CredentialsService
{

    private CredentialsService credentialsService;

    @BeforeEach
    public void setup()
    {
        credentialsService = new CredentialsService();
        credentialsService.jwtMinutesDuration = 30;
        credentialsService.jwtSecret = "abcdefghijklm";
        credentialsService.salty = "salt";
    }

    @Test
    public void shouldGenerateHashPasword()
    {
        String hashPassword = this.credentialsService.generateHashPasword("abcd1234");
        int pass = hashPassword.hashCode();
        assertEquals( hashPassword.hashCode(), -513682244 );
    }

    @Test
    public void shouldGenerateCompleteDifferentHashPasword()
    {
        String hashPassword = this.credentialsService.generateHashPasword("abcd12345");
        int pass = hashPassword.hashCode();
        assertTrue( hashPassword.hashCode() != -513682244 );
    }

    @Test
    public void shouldValidatePasword()
    {
        String hashPassword = this.credentialsService.generateHashPasword("abcd1234");
        credentialsService.validatePassword("abcd1234",hashPassword);
    }

    @Test
    public void shouldgenerateJWT()
    {
        String token = this.credentialsService.generateJsonWebToken(
                JWTWebPayload.builder().userEmail("email@email.com").userName("test").userId(1L).build()
        );
        assertTrue( token != null );
    }


}
