package com.muhammadalsaied.microservices.fixer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by @author Muhammed Alsaied On Oct 27, 2018
 * muhammadalsaied96@gmail.com
 */
@RestController
public class MainController {

    public static final String FIXER_ACCESS_KEY = "718ed5f346c942225f9e57de7faab99f";
    private static Logger LOG = LoggerFactory.getLogger(MainController.class);

    @GetMapping(value = "from/{from}/to/{to}")
    public FixerResponce getResult(@PathVariable String from, @PathVariable String to) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<FixerResponce> responce = restTemplate.exchange(
                    "http://data.fixer.io/api/latest?access_key=718ed5f346c942225f9e57de7faab99f&symbols=" + from + "," + to,
                    HttpMethod.GET, HttpEntity.EMPTY, FixerResponce.class);

            FixerResponce fixerResponce = responce.getBody();
            LOG.info("{}", fixerResponce);
            if (fixerResponce.getSuccess() == true) {
                Double fromRate = fixerResponce.getRates().get(from.toUpperCase());
                Double toRate = fixerResponce.getRates().get(to.toUpperCase());
                if (fromRate == null || toRate == null) {
                    throw new BadRequestException("invalid_currency_codes");
                }
                double multiplier = (1 / fromRate) * toRate;
                return fixerResponce;
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
        throw new BadRequestException("Unknown Error happend!");
    }

    @GetMapping("k")
    public String d() {
        return "hi";
    }
}
