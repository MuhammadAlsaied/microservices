package com.muhammadalsaied.microservices.currencyexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by @author Muhammed Alsaied On Oct 26, 2018
 * muhammadalsaied96@gmail.com
 */
@RestController
public class MainController {
    
    @Autowired
    FixerServiceProxy fixerServiceProxy;
    
    private static Logger LOG = LoggerFactory.getLogger(MainController.class);
    
    @GetMapping(value = "from/{from}/to/{to}/{quantity}")
    public ExchangeValue getResult(@PathVariable String from, @PathVariable String to,
            @PathVariable double quantity) {
        try {
            FixerResponce fixerResponce = fixerServiceProxy.getResult(from, to);
            LOG.info("{}", fixerResponce);
            
            if (fixerResponce.getSuccess() == true) {
                Double fromRate = fixerResponce.getRates().get(from.toUpperCase());
                Double toRate = fixerResponce.getRates().get(to.toUpperCase());
                if (fromRate == null || toRate == null) {
                    throw new BadRequestException("invalid_currency_codes");
                }
                double multiplier = (1 / fromRate) * toRate;
                return new ExchangeValue(from, to, multiplier, quantity);
            }
            throw new BadRequestException(fixerResponce.getError().getType());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
        
    }
}
