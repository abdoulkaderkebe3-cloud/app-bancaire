package Kebe.application_bancaire.Controllers;

import Kebe.application_bancaire.dto.AccountDto;
import Kebe.application_bancaire.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/")
    public ResponseEntity<Integer>save(@RequestBody AccountDto accountDto){
        return ResponseEntity.ok(service.save(accountDto));
    }


    @GetMapping("")
    public ResponseEntity<List<AccountDto>>findAll(){
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("/{account-id}")
    public ResponseEntity<AccountDto>findById(
            @PathVariable("account-id") int addressId
    ){
        return ResponseEntity.ok(service.findById(addressId));
    }


    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void>delete(
            @PathVariable("address-id") int accountId
    ){
        service.delete(accountId);
        return ResponseEntity.accepted().build();
    }




}
