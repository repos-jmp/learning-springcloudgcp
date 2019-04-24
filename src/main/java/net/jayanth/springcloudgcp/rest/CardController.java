package net.jayanth.springcloudgcp.rest;

import lombok.extern.apachecommons.CommonsLog;
import net.jayanth.springcloudgcp.dto.CardDTO;
import net.jayanth.springcloudgcp.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@CommonsLog
@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping(path = "/cards")
    public ResponseEntity<List<CardDTO>> getAllCards() {

        log.info("This is a test log message");
        return ResponseEntity.ok(cardService.getAllCards());
    }

    @PostMapping(path = "/cards")
    public ResponseEntity<CardDTO> addCard(@RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.saveCard(cardDTO));
    }

    @GetMapping(path = "/cards/{id}")
    public ResponseEntity<CardDTO> getCard(@PathVariable(name = "id") String cardId) {
        return ResponseEntity.ok(cardService.getCard(cardId));
    }

    @PutMapping(path = "/cards/{id}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable(name = "id") String cardId, @RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.updateCard(cardId,cardDTO));
    }

    @PostMapping(path = "/cards/image")
    public ResponseEntity<String> addCard(@RequestParam(name="cardId") String cardId,
                                           @RequestParam(name="cardImage") MultipartFile file) throws IOException {
        return ResponseEntity.ok(cardService.savecCardImage(cardId, file));
    }

    /*@DeleteMapping(path = "/card/{id}")
    public ResponseEntity<CardDTO> addeCard(@RequestBody CardDTO cardDTO) {

    }*/
}
