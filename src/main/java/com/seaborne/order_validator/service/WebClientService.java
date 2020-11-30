package com.seaborne.order_validator.service;

import com.seaborne.order_validator.model.StockData;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientService {

    private WebClient webClient;

    public WebClientService(String url) {
        this.webClient = WebClient.create(url);
    }

    Mono<StockData> getData(String ticker){
        return webClient.get()
                .uri("/md/{ticker}", ticker)
                .retrieve()
                .bodyToMono(StockData.class);
    }
}
