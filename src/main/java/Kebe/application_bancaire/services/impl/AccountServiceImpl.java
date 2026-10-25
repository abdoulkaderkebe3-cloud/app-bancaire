package Kebe.application_bancaire.services.impl;

import Kebe.application_bancaire.dto.AccountDto;
import Kebe.application_bancaire.exceptions.ObjectsValidator;
import Kebe.application_bancaire.exceptions.OperationNonPermittedException;
import Kebe.application_bancaire.models.Account;
import Kebe.application_bancaire.repository.AccountRepository;
import Kebe.application_bancaire.services.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private  final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {
        if (dto.getIban() != null){
            throw new OperationNonPermittedException("account cannot be update",
                    "save account",
                    "account",
                    "update not permitted"
            );
        }
        validator.validate(dto);
       Account account = AccountDto.toEntity(dto);
       boolean userHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();
       if(userHasAlreadyAnAccount && account.getUser().isActive() )
           throw new OperationNonPermittedException(
           "il existe deja un compte",
           "creer un compte",
           "account service",
           "account creation"
           );

       account.setIban(generteRandomIban());
        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("no account was found with id" +id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }


    private String generteRandomIban(){

        //generer le iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        //verifier si le iban existe
        boolean ibanExists =  repository.findByIban(iban).isPresent();
          if(ibanExists){
              return generteRandomIban();
          }
        return iban;
    }
}
