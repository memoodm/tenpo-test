package co.com.memoodm.tenpo.service.mock.web.client;

import co.com.memoodm.tenpo.service.mock.web.endpoint.MockServiceEndpoints;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = MockServiceEndpoints.ID_MOCK_SERVICE, path= MockServiceEndpoints.PATH_VALUE)
@LoadBalancerClient(name = MockServiceEndpoints.ID_MOCK_SERVICE)
public interface MockValuesControllerClient {

    @GetMapping("percentage")
    public ResponseEntity<Double> getPercentageValue();

    @PutMapping("/{percentage}")
    public ResponseEntity<?> updateValue(@PathVariable("percentage") Double percentage);

    @PutMapping("/setSuccessStatus")
    public ResponseEntity<String> setSuccessStatus();

    @PutMapping("/setFailureStatus")
    public ResponseEntity<String> setFailureStatus();

}
