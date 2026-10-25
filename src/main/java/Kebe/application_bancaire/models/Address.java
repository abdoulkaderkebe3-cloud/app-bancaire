package Kebe.application_bancaire.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class Address extends  Mere {



    private String street ;

    private int houseNumber;

    private String city;

    private int zipCode;

    private String country;

    @OneToOne
    @JoinColumn(name="id_user")
    private User user;


}
