package com.muhammadalsaied.microservices.fixer;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by @author Muhammed Alsaied On Oct 27, 2018
 * muhammadalsaied96@gmail.com
 */
@RestController
public class MainController {

    @Value("${fixer-access-key}")
    private String FIXER_ACCESS_KEY;
    private String FIXER_URI = "http://data.fixer.io/api/latest?access_key={key}&symbols={from},{to}";
    private static Logger LOG = LoggerFactory.getLogger(MainController.class);

    @GetMapping(value = "from/{from}/to/{to}")
    public FixerResponce getResult(@PathVariable String from, @PathVariable String to) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("key", FIXER_ACCESS_KEY);
        params.put("from", from);
        params.put("to", to);
        URI uri = UriComponentsBuilder.fromUriString(FIXER_URI).build(params);

        try {
            ResponseEntity<FixerResponce> responce = restTemplate.exchange(uri, HttpMethod.GET,
                    HttpEntity.EMPTY, FixerResponce.class);
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
