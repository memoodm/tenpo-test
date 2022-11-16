package co.com.memoodm.tenpo.service.user.service;

import co.com.memoodm.tenpo.service.user.web.client.JWTResponse;
import co.com.memoodm.tenpo.service.user.web.client.LogInRequest;
import co.com.memoodm.tenpo.service.user.web.client.User;
import co.com.memoodm.tenpo.service.user.web.client.JWTWebPayload;
import co.com.memoodm.tenpo.service.user.entity.UserEntity;
import co.com.memoodm.tenpo.service.user.exceptions.EmailDuplicatedException;
import co.com.memoodm.tenpo.service.user.exceptions.UserEmailDontExistException;
import co.com.memoodm.tenpo.service.user.mapper.UserMapper;
import co.com.memoodm.tenpo.service.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private UserRepository userRepository;
    private CredentialsService credentialsService;

    @Autowired
    public UserService(
            UserRepository userRepository,
            CredentialsService credentialsService)
    {
        this.userRepository = userRepository;
        this.credentialsService = credentialsService;
    }

    public User createUser(User user)
    {
        if(this.userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new EmailDuplicatedException("Duplicated email");
        }
        UserEntity entity = UserMapper.dtoToEntity( user );
        entity.setPassword( this.credentialsService.generateHashPasword( user.getPassword() ) );
        entity.setCreated( new Date() );
        UserEntity entityCreated = this.userRepository.save( entity );
        return UserMapper.entityToDto( entityCreated );
    }

    public JWTResponse logIn(LogInRequest logInRequest)
    {
        UserEntity user = this.userRepository
                .findByEmail( logInRequest.getEmail() )
                .orElseThrow( () -> new UserEmailDontExistException("No user using the input email") );
        this.credentialsService.validatePassword( logInRequest.getPassword(),user.getPassword() );
        String jwt = this.credentialsService.generateJsonWebToken(
                JWTWebPayload.builder()
                        .userId( user.getId() )
                        .userName( user.getEmail() )
                        .userEmail( user.getName() )
                        .build()
        );
        return JWTResponse
                .builder()
                .payload(jwt)
                .build();
    }
}
