package Kebe.application_bancaire.repository;

import Kebe.application_bancaire.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  @Override
  Optional<User> findById(Integer integer);

  Optional<User> findByEmail(String email);

  List<User> findAllByFirstName(String firstName);

  List<User> findAllByFirstNameContaining(String firstname);

  List<User> findAllByFirstNameContainingIgnoreCase(String firstname);


  List<User> findAllByAccountIban(String iban);
  ;

  User findByFirstNameContainingIgnoreCaseAndEmail(String firstName, String email);


  @Query("from User where firstName = :fn ")
  List<User>searchByFirstNameContaining(@Param("fn") String firstname);

}
