package Kebe.application_bancaire.repository;

import Kebe.application_bancaire.models.Account;
import Kebe.application_bancaire.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByIban(String iban);


    Optional<Account> findByUserId(Integer id);
}
