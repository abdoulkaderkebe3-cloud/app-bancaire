package Kebe.application_bancaire.Controllers;


import Kebe.application_bancaire.dto.UserDto;
import Kebe.application_bancaire.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;


    @PostMapping
    public ResponseEntity<Integer> save(
            //le corps
          @RequestBody UserDto userDto
    ){
        return ResponseEntity.ok(service.save(userDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> findById(
            @PathVariable("user-id") Integer UserId
    ){
        return ResponseEntity.ok(service.findById((UserId)));
    }


    @PatchMapping("/validate/{user-id}")
    public ResponseEntity<Integer>validateAccount(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.validerCompte(userId));
    }

    @PatchMapping("/invalidate/{user-id}")
    public ResponseEntity<Integer>invalidateAccount(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.invaliderCompte(userId));
    }


    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void>delete(
            @PathVariable("user-id") Integer userId
    ){
        service.delete(userId);
       return  ResponseEntity.accepted().build();
    }
}
