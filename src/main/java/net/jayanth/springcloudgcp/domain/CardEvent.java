package net.jayanth.springcloudgcp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardEvent {

    private String eventType;
    private String eventPayload;
}
