package Kebe.application_bancaire.Controllers;


import Kebe.application_bancaire.dto.AuthenticationResponse;
import Kebe.application_bancaire.dto.AuthenticationResquest;
import Kebe.application_bancaire.dto.UserDto;
import Kebe.application_bancaire.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserDto user
    ){
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
         @RequestBody AuthenticationResquest request
    ){
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
