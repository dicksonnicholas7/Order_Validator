package com.seaborne.order_validator.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seaborne.consumervalidator.SendOrderRequest;
import com.seaborne.consumervalidator.SendOrderResponse;
import com.seaborne.order_validator.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import redis.clients.jedis.Jedis;


@Endpoint
public class OrderEndpoint{

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String NAMESPACE_URI = "http://seaborne.com/ConsumerValidator";


    private OrderServiceImpl orderService;

    @Autowired
    public OrderEndpoint(OrderServiceImpl orderService){
        this.orderService = orderService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "sendOrderRequest")
    @ResponsePayload
    public SendOrderResponse getOrders(@RequestPayload SendOrderRequest order){
        SendOrderResponse response = new SendOrderResponse();
        if(orderService.isOrderValid(order)){

            //TODO: publish to trading via redis
            try{
                Jedis client = new Jedis("localhost", 9090);
                client.publish("validation", objectMapper.writeValueAsString(order));
            }catch(Exception e){

            }finally {
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("valid");
            }

        }else{

            response.setStatusCode(HttpStatus.FORBIDDEN.value());
            response.setMessage("invalid");

        }
        return response;
    }
}
