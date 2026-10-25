package Kebe.application_bancaire.Controllers;

import Kebe.application_bancaire.dto.ContactDto;
import Kebe.application_bancaire.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;
    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody ContactDto contactDto){
        return ResponseEntity.ok(service.save(contactDto));
    }


    @GetMapping("")
    public ResponseEntity<List<ContactDto>>findAll(){
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("/{contact-id}")
    public ResponseEntity<ContactDto>findById(
            @PathVariable("contact-id") int contactId
    ){
        return ResponseEntity.ok(service.findById(contactId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<ContactDto>>findAllByUserId(
            @PathVariable("user-id") int userId
    ){
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }


    @DeleteMapping("/{contact-id}")
    public ResponseEntity<Void>delete(
            @PathVariable("contact-id") int contactId
    ){
        service.delete(contactId);
        return ResponseEntity.accepted().build();
    }

}
