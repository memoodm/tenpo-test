package co.com.memoodm.tenpo.service.calculator.client;

import co.com.memoodm.tenpo.service.mock.web.client.MockValuesControllerClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SubaClient
{

    private MockValuesControllerClient mockClient;

    @Autowired
    public SubaClient(MockValuesControllerClient mockClient)
    {
        this.mockClient = mockClient;
    }

    @Cacheable("${cache.value.suba}")
    public Double getValueByCaching()
    {
        return getValue();
    }

    @CachePut(value="${cache.value.suba}")
    @CircuitBreaker(name="subamicroserviceCircuitBreaker", fallbackMethod = "fallbackMethod")
    @Retry(name="subamicroserviceRetry")
    public Double getValue()
    {
        return mockClient.getPercentageValue().getBody();
    }

    public Double fallbackMethod(Exception e)
    {
        return null;
    }

}
