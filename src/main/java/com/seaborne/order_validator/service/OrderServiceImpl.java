package com.seaborne.order_validator.service;

import com.seaborne.consumervalidator.SendOrderRequest;
import com.seaborne.order_validator.model.StockData;
import org.springframework.stereotype.Service;



@Service
public class OrderServiceImpl implements OrderService{

    @Override
    public Boolean isOrderValid(SendOrderRequest orderRequest) {

        Boolean isOrderValid = false;


        WebClientService webClientService = new WebClientService("https://exchange.matraining.com");
        WebClientService webClientService1 = new WebClientService("https://exchange2.matraining.com");

        StockData stockdata1 = webClientService.getData(orderRequest.getProduct()).block();
        StockData stockdata2 = webClientService1.getData(orderRequest.getProduct()).block();

        StockData AvData =
                new StockData(
                        (stockdata1.getBidPrice()+stockdata2.getBidPrice())/2,
                        (stockdata1.getAskPrice()+stockdata2.getAskPrice())/2,
                        (stockdata2.getBuyLimit() + stockdata1.getBuyLimit())/2,
                        orderRequest.getProduct(),
                        (stockdata1.getSellLimit() + stockdata2.getSellLimit())/2,
                        Math.max(stockdata1.getLastTradedPrice(), stockdata2.getLastTradedPrice()),
                        Math.max(stockdata1.getMaxPriceShift(), stockdata2.getMaxPriceShift())
                );



        if(orderRequest.getSide().matches("BUY")){
            if(AvData.getBidPrice()!=0){
                if (((orderRequest.getPrice()/AvData.getBidPrice())*100 < 120)){
                    if(AvData.getBuyLimit() > 0){
                        if(orderRequest.getQuantity() < AvData.getBuyLimit()){
                            isOrderValid = true;
                        }
                    }else{
                            isOrderValid = true;
                    }
                }
            }else{
                if (((orderRequest.getPrice()*100) < 120)){
                    if(orderRequest.getQuantity() < AvData.getBuyLimit()){
                        if(AvData.getBuyLimit() > 0){
                            if(orderRequest.getQuantity() < AvData.getBuyLimit()){
                                isOrderValid = true;
                            }
                        }else{
                            isOrderValid = true;
                        }
                    }
                }
            }
        }

        if(orderRequest.getSide().matches("SELL")){
            if(AvData.getAskPrice()!=0){
                if (((orderRequest.getPrice() / AvData.getAskPrice()) * 100) > AvData.getAskPrice() * 120) {
                    if(AvData.getSellLimit() > 0) {
                        if (orderRequest.getQuantity() < AvData.getSellLimit()) {
                            isOrderValid = true;
                        }
                    }else{
                            isOrderValid = true;
                    }
                }
            }else{
                if ((orderRequest.getPrice() * 100) > AvData.getAskPrice() * 120) {
                    if(AvData.getSellLimit() > 0) {
                        if (orderRequest.getQuantity() < AvData.getSellLimit()) {
                            isOrderValid = true;
                        }
                    }else{
                        isOrderValid = true;
                    }
                }
            }

        }

        return isOrderValid;

        }
}
