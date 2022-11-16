package co.com.memoodm.tenpo.service.user.service;

import co.com.memoodm.tenpo.service.user.web.client.JWTWebPayload;
import co.com.memoodm.tenpo.service.user.exceptions.GeneratePasswordException;
import co.com.memoodm.tenpo.service.user.exceptions.WrongPasswordException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Service
public class CredentialsService
{

    @Value("${security.passwords.salt}")
    protected String salty;

    @Value("${security.value.jwt.secret}")
    protected String jwtSecret;

    @Value("${security.value.jwt.expiration.minutes}")
    protected Integer jwtMinutesDuration;

    private final int HASH_GENERATION_ITERATIONS = 65536;
    private final int HASH_GENERATION_KEY_LENGTH = 128;
    private final String HASH_GENERATION_ALGORITHM = "PBKDF2WithHmacSHA1";

    public String generateHashPasword(String password)
    {
        try {
            String saltyPassword = salty + password + salty;
            KeySpec spec = new PBEKeySpec(
                    password.toCharArray(),
                    saltyPassword.getBytes(StandardCharsets.UTF_8),
                    HASH_GENERATION_ITERATIONS,
                    HASH_GENERATION_KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance( HASH_GENERATION_ALGORITHM );
            byte[] hash = factory.generateSecret( spec ).getEncoded();
            return new String(hash, StandardCharsets.UTF_8);
        }
        catch (Exception e){
            throw new GeneratePasswordException(e.getMessage());
        }
    }

    public void validatePassword(String plainTextPassword, String encodedPasword) throws WrongPasswordException
    {
        if( !encodedPasword.equals( this.generateHashPasword(plainTextPassword) ) )
        {
            throw new WrongPasswordException("The input password dont match the user account one");
        }
    }

    public String generateJsonWebToken(JWTWebPayload jwtInformation)
    {
        ObjectMapper mapObject = new ObjectMapper();
        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .addClaims( mapObject.convertValue( jwtInformation, HashMap.class ) )
                .setIssuedAt( Date.from(now) )
                .setExpiration( Date.from( now.plus(jwtMinutesDuration, ChronoUnit.MINUTES) ))
                .signWith(
                        HS256,
                        TextCodec.BASE64.decode(jwtSecret)
                )
                .compact();
        return jwtToken;
    }

    public void isValidJWT(String token) throws IOException
    {
        SignatureAlgorithm sa = HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(jwtSecret.getBytes(), sa.getJcaName());
        String[] chunks = token.split("\\.");
        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];
        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);
        if (!validator.isValid(tokenWithoutSignature, signature)) {
            throw new IOException("Could not verify JWT token integrity!");
        }
    }

}
