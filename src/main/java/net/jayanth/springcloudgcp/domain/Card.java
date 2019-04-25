package net.jayanth.springcloudgcp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jayanth.springcloudgcp.dto.CardDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Card number is missing")
    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @NotBlank(message = "Issuing bank is missing")
    @Column(name = "ISSUING_BANK")
    private String issuingBank;

    @NotBlank(message = "Card Owner is missing")
    @Column(name = "CARD_OWNER")
    private String cardOwner;

    public Card(CardDTO cardDTO) {
        this.id = cardDTO.getId();
        this.cardNumber = cardDTO.getCardNumber();
        this.cardOwner = cardDTO.getCardOwner();
        this.issuingBank = cardDTO.getIssuingBank();
    }

}
