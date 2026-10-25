package Kebe.application_bancaire.dto;


import lombok.Data;

@Data
public class AuthenticationResquest {

    private String email;

    private String password;
}
