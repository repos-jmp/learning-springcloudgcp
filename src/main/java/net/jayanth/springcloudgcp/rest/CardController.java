package net.jayanth.springcloudgcp.rest;


import net.jayanth.springcloudgcp.dto.CardDTO;
import net.jayanth.springcloudgcp.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/")
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping(path = "/cards")
    public ResponseEntity<List<CardDTO>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }

    @PostMapping(path = "/cards")
    public ResponseEntity<CardDTO> addeCard(@RequestBody CardDTO cardDTO) {
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

    /*@DeleteMapping(path = "/card/{id}")
    public ResponseEntity<CardDTO> addeCard(@RequestBody CardDTO cardDTO) {

    }*/
}
