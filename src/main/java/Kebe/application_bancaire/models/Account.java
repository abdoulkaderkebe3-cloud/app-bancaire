package Kebe.application_bancaire.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Account extends Mere {



    private String iban;


    @OneToOne
    @JoinColumn(name="id_user")
    private User user;

}
