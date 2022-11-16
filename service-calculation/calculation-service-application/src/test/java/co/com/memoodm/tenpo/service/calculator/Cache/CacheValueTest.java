package co.com.memoodm.tenpo.service.calculator.Cache;

import co.com.memoodm.tenpo.service.calculator.client.SubaClient;
import co.com.memoodm.tenpo.service.calculator.exception.CantObtainSubaValueException;
import co.com.memoodm.tenpo.service.calculator.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CacheValueTest
{

    @Autowired
    CacheManager cacheManager;

    PriceService priceService;

    @Mock
    SubaClient subaClient;

    @BeforeEach
    public void setup()
    {
        this.priceService = new PriceService( subaClient );
    }

    @Test
    public void shouldGetSubaValue()
    {
        Mockito.when(subaClient.getValueByCaching()).thenReturn(null);
        Mockito.when(subaClient.getValue()).thenReturn(10D);
        Double suba = priceService.getSubaValue();
        assertTrue( suba == 1.1 );
    }

    @Test
    public void shouldGetSubaCachingValue()
    {
        Mockito.when(subaClient.getValueByCaching()).thenReturn(10D);
        Double suba = priceService.getSubaValue();
        assertTrue( suba == 1.1 );
    }

    @Test
    public void shouldFailForNoCachingValue()
    {
        Mockito.when(subaClient.getValueByCaching()).thenThrow( new CantObtainSubaValueException("") );
        Exception exception = assertThrows(CantObtainSubaValueException.class, () -> {
            priceService.getSubaValue();
        });
    }

    @Test
    public void getPriceValue()
    {
        Mockito.when(subaClient.getValueByCaching()).thenReturn(10D);
        Mockito.when(subaClient.getValue()).thenReturn(10D);
        Double price = priceService.getTotalPrice(5.0,5.0);
        assertTrue( price == 11 );
    }

}
