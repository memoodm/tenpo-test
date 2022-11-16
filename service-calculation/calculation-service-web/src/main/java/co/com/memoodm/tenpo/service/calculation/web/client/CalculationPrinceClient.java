package co.com.memoodm.tenpo.service.calculation.web.client;

import co.com.memoodm.tenpo.service.calculation.web.endpoint.CalculationServiceEndpoints;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = CalculationServiceEndpoints.ID_MOCK_SERVICE, path= CalculationServiceEndpoints.PATH_PRICE)
@LoadBalancerClient(name = CalculationServiceEndpoints.ID_MOCK_SERVICE)
public interface CalculationPrinceClient
{

    @GetMapping
    public Double getTotalPrice(@RequestParam(name="number1",required = true) Double number1, @RequestParam(name="number2",required = true) Double number2);

}

