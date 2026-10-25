package Kebe.application_bancaire.Controllers;


import Kebe.application_bancaire.dto.AddressDto;
import Kebe.application_bancaire.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @PostMapping("/")
    public ResponseEntity<Integer>save(@RequestBody AddressDto addressDto){
        return ResponseEntity.ok(service.save(addressDto));
    }


    @GetMapping
    public ResponseEntity<List<AddressDto>>findAll(){
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("/{address-id}")
    public ResponseEntity<AddressDto>findById(
            @PathVariable("address-id") int addressId
    ){
        return ResponseEntity.ok(service.findById(addressId));
    }


    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void>delete(
            @PathVariable("address-id") int addrressId
    ){
        service.delete(addrressId);
        return ResponseEntity.accepted().build();
    }


}
