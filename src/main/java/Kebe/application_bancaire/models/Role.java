package Kebe.application_bancaire.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role  extends Mere {



    private String name;


    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
