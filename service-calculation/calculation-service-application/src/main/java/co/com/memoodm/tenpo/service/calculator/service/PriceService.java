package co.com.memoodm.tenpo.service.calculator.service;

import co.com.memoodm.tenpo.service.calculator.client.SubaClient;
import co.com.memoodm.tenpo.service.calculator.exception.CantObtainSubaValueException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PriceService {

    protected SubaClient subaClient;

    @Autowired
    public PriceService(SubaClient subaClient)
    {
        this.subaClient = subaClient;
    }

    public Double getTotalPrice(Double number1, Double number2)
    {
        return (number1+number2)*getSubaValue();
    }

    public Double getSubaValue(){
        try
        {
            Double decimalValue = subaClient.getValueByCaching();
            if(decimalValue==null){
                decimalValue = subaClient.getValue();
            }
            return decimalValue / 100 + 1;
        }
        catch(Exception e)
        {
            log.error("Getting suba percentage value: "+e.getMessage());
            throw new CantObtainSubaValueException( e.getMessage() );
        }
    }

    @CacheEvict(value="${cache.value.suba}", allEntries=true)
    @Scheduled(fixedDelay = 60000)
    public void updateValue()
    {
        this.subaClient.getValue();
    }

}