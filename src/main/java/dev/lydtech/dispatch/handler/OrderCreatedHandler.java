package dev.lydtech.dispatch.handler;

import dev.lydtech.dispatch.message.OrderCreated;
import dev.lydtech.dispatch.service.DispatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreatedHandler {

    private final DispatchService dispatchService;

    public OrderCreatedHandler(DispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @KafkaListener(id = "orderConsumerClient", topics = {"order.created"}, groupId = "dispatch.order.created.consumer",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(OrderCreated payload) {
        log.info("Received message payload: " + payload);
        dispatchService.process(payload);
    }
}
