package net.jayanth.springcloudgcp.rest;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway(defaultRequestChannel = "cardEventsOutputChannel")
public interface OutBoundGateway {
    void publishMessage(String message);
}
