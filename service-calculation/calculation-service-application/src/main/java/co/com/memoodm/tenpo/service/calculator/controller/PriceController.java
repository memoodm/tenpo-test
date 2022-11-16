package co.com.memoodm.tenpo.service.calculator.controller;

import co.com.memoodm.tenpo.service.calculation.web.client.CalculationPrinceClient;
import co.com.memoodm.tenpo.service.calculation.web.endpoint.CalculationServiceEndpoints;
import co.com.memoodm.tenpo.service.calculator.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CalculationServiceEndpoints.PATH_PRICE)
public class PriceController implements CalculationPrinceClient
{

    private PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService)
    {
        this.priceService = priceService;
    }

    @Override
    public Double getTotalPrice(Double number1, Double number2)
    {
        return this.priceService.getTotalPrice(number1,number2);
    }

}
