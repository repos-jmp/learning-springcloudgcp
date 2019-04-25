package net.jayanth.springcloudgcp.rest;

import lombok.extern.apachecommons.CommonsLog;
import net.jayanth.springcloudgcp.dto.CardDTO;
import net.jayanth.springcloudgcp.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.IOException;


@CommonsLog
@RestController
@Validated
@RequestMapping("/v1/protected/") //TODO Migrate to http content type versioning as matuity model increases.
public class CardController {

    @Autowired
    CardService cardService;

    //TODO Validated paging and sorting parameters
    @GetMapping(path = "/cards")
    public ResponseEntity<Page<CardDTO>> getCards(
            @RequestParam(name="orderBy", required=false, defaultValue ="id") String orderBy,
            @RequestParam(name="direction", required=false, defaultValue ="asc") String direction,
            @RequestParam(name="page", required=false, defaultValue ="0") Integer page,
            @RequestParam(name="size", required=false, defaultValue ="10") Integer size
    ) {

        log.info("This is a test log message");
        return ResponseEntity.ok(cardService.getCards(orderBy, direction, page, size));
    }

    @PostMapping(path = "/cards")
    public ResponseEntity<CardDTO> addCard(@Valid @RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.saveCard(cardDTO));
    }

    @GetMapping(path = "/cards/{id}")
    public ResponseEntity<CardDTO> getCard(@PathVariable(name = "id") @NotBlank @Size(min = 1)String cardId) {
        return ResponseEntity.ok(cardService.getACard(cardId));
    }

    @PutMapping(path = "/cards/{id}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable(name = "id") @NotBlank @Size(min = 1) String cardId,
                                              @Valid @RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.updateCard(cardId,cardDTO));
    }

    @PostMapping(path = "/cards/image")
    public ResponseEntity<String> addCard(@RequestParam(name="cardId") @NotBlank @Size(min = 1) String cardId,
                                           @RequestParam(name="cardImage") MultipartFile file) throws IOException {
        return ResponseEntity.ok(cardService.savecCardImage(cardId, file));
    }

    //TODO Soft delete logic
    //TODO Auditing logic createdAt & updatedAt
    /*@DeleteMapping(path = "/card/{id}")
    public ResponseEntity<CardDTO> addeCard(@RequestBody CardDTO cardDTO) {
    }*/
}
