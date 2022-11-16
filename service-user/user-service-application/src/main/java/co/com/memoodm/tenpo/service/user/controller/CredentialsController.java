package co.com.memoodm.tenpo.service.user.controller;

import co.com.memoodm.tenpo.service.user.web.client.JWTResponse;
import co.com.memoodm.tenpo.service.user.web.client.UserCredentialsClient;
import co.com.memoodm.tenpo.service.user.web.client.LogInRequest;
import co.com.memoodm.tenpo.service.user.web.client.User;
import co.com.memoodm.tenpo.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credentials")
public class CredentialsController implements UserCredentialsClient
{
	private UserService userService;

	@Autowired
	public CredentialsController(UserService userService)
	{
		this.userService = userService;
	}

	@Override
	public ResponseEntity<User> signIn(User user)
	{
		return new ResponseEntity( userService.createUser(user) , HttpStatus.OK );
	}

	@Override
	public ResponseEntity<JWTResponse> logIn(LogInRequest logInRequest)
	{
		return new ResponseEntity( userService.logIn(logInRequest) , HttpStatus.OK );
	}

}
