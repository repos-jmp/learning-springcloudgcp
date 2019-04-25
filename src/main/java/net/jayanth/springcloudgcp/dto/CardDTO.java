package net.jayanth.springcloudgcp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private Long id;

    @Size(min=14)
    @NotBlank(message = "Card number is missing")
    private String cardNumber;

    @Size(min=3)
    @NotBlank(message = "Issuing bank is missing")
    private String issuingBank;

    @Size(min=3)
    @NotBlank(message = "Card Owner is missing")
    private String cardOwner;

}
