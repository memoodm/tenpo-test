package co.com.memoodm.tenpo.service.user.web.client;

import co.com.memoodm.tenpo.service.user.web.endpoint.UserServiceEndpoints;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = UserServiceEndpoints.ID_USER_SERVICE, path= UserServiceEndpoints.PATH_VALUE)
@LoadBalancerClient(name = UserServiceEndpoints.ID_USER_SERVICE)
public interface UserCredentialsClient {

    @PostMapping("/signIn")
    ResponseEntity<User> signIn(@RequestBody User user);

    @PostMapping("/logIn")
    ResponseEntity<JWTResponse> logIn(@RequestBody LogInRequest logInRequest);

}
