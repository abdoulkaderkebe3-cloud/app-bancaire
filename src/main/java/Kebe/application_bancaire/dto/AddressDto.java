package Kebe.application_bancaire.dto;


import Kebe.application_bancaire.models.Address;
import Kebe.application_bancaire.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddressDto {

    private Integer id;

    private String street ;

    private  int houseNumber;

    private String city;

    private Integer zipCode;

    private String country;

    private Integer userid;

    public  static AddressDto fromEntity(Address address){

        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .userid(address.getUser().getId())
                .build();

    }


    public  static Address toEntity(AddressDto address){

        return Address.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .user(
                        User.builder()
                                .id(address.getUserid())
                                .build()
                )
                .build();

    }
}
