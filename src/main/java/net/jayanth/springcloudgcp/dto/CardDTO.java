package net.jayanth.springcloudgcp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private Long id;
    private String cardNumber;
    private String issuingBank;
    private String cardOwner;

}
