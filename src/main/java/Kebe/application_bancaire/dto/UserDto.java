package Kebe.application_bancaire.dto;


import Kebe.application_bancaire.models.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private Integer id;


    @NotNull
    @NotEmpty
    @NotBlank
    private String firstname;

    @NotNull(message = "le prenom ne doit pas etre vide")
    @NotEmpty(message = "le prenom ne doit pas etre vide")
    @NotBlank(message = "le prenom ne doit pas etre vide")
    private String lastname;

    @NotNull
    @NotEmpty
    @NotBlank(message = "entrer une vraie address")
    @Email()
    private String email;


    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=8, max=16)
    private String password;

    //private String iban;

    public static UserDto fromEntity(User user){
        //if (user == null) return null;
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                //.iban(user.getAccount() != null ? user.getAccount().getIban() : null)
                .build();
    }
    public static User toEntity(UserDto user){
        return User.builder()
                 .id(user.getId())
                .firstName((user.getFirstname()))
                .lastName((user.getLastname()))
                .email(user.getEmail())
                .password(user.getPassword())
                .active(true)
                .build();
    }
}
