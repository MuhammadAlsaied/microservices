package com.muhammadalsaied.microservices.currencyexchange;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by @author Muhammed Alsaied On Oct 27, 2018
 * muhammadalsaied96@gmail.com
 */

@FeignClient(name = "zuul-server")
@RibbonClient(name = "zuul-server")
public interface FixerServiceProxy {

    @GetMapping("fixer-service/from/{from}/to/{to}")
    public FixerResponce getResult(@PathVariable("from") String from,
            @PathVariable("to") String to);

}
