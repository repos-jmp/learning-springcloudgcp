package net.jayanth.springcloudgcp.dto;

public class CardDTO {

    private Long id;
    private String cardNumber;
    private String issuingBank;
    private String cardOwner;

    public CardDTO(Long id, String cardNumber, String issuingBank, String cardOwner) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.issuingBank = issuingBank;
        this.cardOwner = cardOwner;
    }

    public CardDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getIssuingBank() {
        return issuingBank;
    }

    public void setIssuingBank(String issuingBank) {
        this.issuingBank = issuingBank;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }
}
