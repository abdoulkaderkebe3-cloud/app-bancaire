package Kebe.application_bancaire.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contact extends  Mere {

    private String firstName;

    private String lastName;

    private String email;

    private String iban;


    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


}
