package Kebe.application_bancaire.dto;

import Kebe.application_bancaire.models.Account;
import lombok.*;

import java.sql.ConnectionBuilder;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Integer id;

    private String iban;

    private UserDto user;

    public static AccountDto fromEntity(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .iban(account.getIban())
                .user(UserDto.fromEntity(account.getUser()))
                .build();
    }

    public static Account toEntity(AccountDto account){
        return Account.builder()
                .id(account.getId())
                .iban(account.getIban())
                .user(UserDto.toEntity(account.getUser()))
                .build();
    }

}
