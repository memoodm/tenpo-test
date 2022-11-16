package com.tenpo.mock.service;

import com.tenpo.mock.util.exceptions.SimulateServerErrorException;
import com.tenpo.mock.value.service.ValueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ValueServiceTest extends ValueService
{

    private ValueService valueService;

    @BeforeEach
    public void setup()
    {
        this.valueService = this;
        this.forceFailing = false;
        this.percentage = 10D;
    }

    @Test
    public void shouldGetValue()
    {
        Double value = valueService.getValue();
        assertTrue( value == 10D );
    }

    @Test
    public void shouldUpdateValue()
    {
        Double value = valueService.updatePercentage(20D);
        assertTrue( value == 20D );
    }

    @Test
    public void shouldForceFail()
    {
        valueService.setFailureStatus();
        Exception exception = assertThrows(SimulateServerErrorException.class, () -> {
            valueService.getValue();
        });
        assertTrue( exception instanceof SimulateServerErrorException );
    }

    @Test
    public void shouldRecoverAfterSetForceFail()
    {
        valueService.setFailureStatus();
        valueService.setSuccessStatus();
        Double value = valueService.getValue();
        assertTrue( value == 10D );
    }

}
