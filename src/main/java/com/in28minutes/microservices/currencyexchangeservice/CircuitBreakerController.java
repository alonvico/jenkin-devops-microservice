package com.in28minutes.microservices.currencyexchangeservice;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    @Retry(name="sample-api", fallbackMethod  = "fallbackResponse") //"default" intenta 3 veces
    public String sampleApi() {

        logger.info("Sample API call received!!!");

        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);

        return forEntity.getBody();
    }

    public String fallbackResponse(Throwable throwable) {
        return "Respuesta de respaldo (fallback): "+throwable.toString();
    }


}
