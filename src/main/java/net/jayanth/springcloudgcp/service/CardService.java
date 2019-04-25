package net.jayanth.springcloudgcp.service;


import lombok.extern.apachecommons.CommonsLog;
import net.jayanth.springcloudgcp.dao.CardRepository;
import net.jayanth.springcloudgcp.domain.Card;
import net.jayanth.springcloudgcp.domain.CardEvent;
import net.jayanth.springcloudgcp.dto.CardDTO;
import net.jayanth.springcloudgcp.rest.OutBoundGateway;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.core.GcpProjectIdProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.WritableResource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CommonsLog
@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private OutBoundGateway outBoundGateway;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private GcpProjectIdProvider projectIdProvider;


    public CardDTO getACard(String cardId){
        Optional<Card> card = cardRepository.findById(Long.valueOf(cardId));
        if(!card.isPresent())
            throw new EntityNotFoundException();
        return convertCardToDto(card.get());
    }

    public Page<CardDTO> getCards(String orderBy, String sortDirection, int page, int size){

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), orderBy);
        Page<CardDTO> pageofCards = cardRepository.findAll(pageable).map(this::convertCardToDto);
        return  pageofCards;
    }

    public CardDTO updateCard(String cardId, CardDTO cardDTO){
        Optional<Card> existingCard = cardRepository.findById(Long.valueOf(cardId));
        BeanUtils.copyProperties(existingCard,  new Card(cardDTO));
        Card updatedCard = cardRepository.save(existingCard.get());
        CardEvent cardEvent = new CardEvent("card-updated", String.valueOf(updatedCard.getId()));
        outBoundGateway.publishMessage(cardEvent.toString());
        return convertCardToDto(updatedCard);
    }

    public CardDTO saveCard(CardDTO cardDTO){
        Card newCard = new Card(cardDTO);
        newCard = cardRepository.save(newCard);
        CardEvent cardEvent = new CardEvent("card-created", String.valueOf(newCard.getId()));
        outBoundGateway.publishMessage(cardEvent.toString());
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

    public String savecCardImage(String cardId, MultipartFile file) throws IOException {

        String filename = null;
        if (file != null && !file.isEmpty()
                && file.getContentType().equals("image/png")) {

            String bucket = "gs://" + projectIdProvider.getProjectId();

            filename = UUID.randomUUID().toString() + ".jpg";
            WritableResource resource = (WritableResource)
                    context.getResource(bucket + "/" + filename);

            // Write the file to Cloud Storage using WritableResource
            try (OutputStream os = resource.getOutputStream()) {
                os.write(file.getBytes());
            }
        }
        return filename;
    }
}
