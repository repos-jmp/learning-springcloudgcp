package net.jayanth.springcloudgcp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jayanth.springcloudgcp.dto.CardDTO;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "ISSUING_BANK")
    private String issuingBank;

    @Column(name = "CARD_OWNER")
    private String cardOwner;

    public Card(CardDTO cardDTO) {
        this.id = cardDTO.getId();
        this.cardNumber = cardDTO.getCardNumber();
        this.cardOwner = cardDTO.getCardOwner();
        this.issuingBank = cardDTO.getIssuingBank();
    }

}
