package net.jayanth.springcloudgcp.service;


import net.jayanth.springcloudgcp.dao.CardRepository;
import net.jayanth.springcloudgcp.domain.Card;
import net.jayanth.springcloudgcp.dto.CardDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public CardDTO getCard(String cardId){

        Optional<Card> card = cardRepository.findById(Long.valueOf(cardId));
        if(!card.isPresent())
            throw new EntityNotFoundException();
        return convertCardToDto(card.get());
    }

    public List<CardDTO> getAllCards(){

        Optional<List<Card>> cards = Optional.ofNullable(cardRepository.findAll());
        if(!cards.isPresent())
            throw new EntityNotFoundException();
        return  convertCardstoDTOs(cards.get());
    }

    public CardDTO updateCard(String cardId, CardDTO cardDTO){
        Optional<Card> existingCard = cardRepository.findById(Long.valueOf(cardId));
        BeanUtils.copyProperties(existingCard,  new Card(cardDTO));
        Card updatedCard = cardRepository.save(existingCard.get());
        return convertCardToDto(updatedCard);
    }

    public CardDTO saveCard(CardDTO cardDTO){
        Card newCard = new Card(cardDTO);
        newCard = cardRepository.save(newCard);
        return convertCardToDto(newCard);
    }


    private CardDTO convertCardToDto(Card card) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(card, CardDTO.class);
    }

    private List<CardDTO> convertCardstoDTOs(List<Card> cards) {
        ModelMapper modelMapper = new ModelMapper();
        return  cards.stream()
                .map(crd -> modelMapper.map(crd, CardDTO.class))
                .collect(Collectors.toList());
    }
}
