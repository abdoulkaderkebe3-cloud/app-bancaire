package Kebe.application_bancaire.Controllers;


import Kebe.application_bancaire.dto.TransactionDto;
import Kebe.application_bancaire.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(service.save(transactionDto));
    }


    @GetMapping
    public ResponseEntity<List<TransactionDto>>findAll(){
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("/{transaction-id}")
    public ResponseEntity<TransactionDto>findById(
            @PathVariable("transaction-id") int transactionId
    ){
        return ResponseEntity.ok(service.findById(transactionId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<TransactionDto>>findAllByUserId(
            @PathVariable("user-id") int userId
    ){
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }


    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void>delete(
            @PathVariable("address-id") int transactionId
    ){
        service.delete(transactionId);
        return ResponseEntity.accepted().build();
    }
}
