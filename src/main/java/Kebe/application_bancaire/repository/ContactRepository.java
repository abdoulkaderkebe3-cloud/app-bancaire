package Kebe.application_bancaire.repository;

import Kebe.application_bancaire.dto.ContactDto;
import Kebe.application_bancaire.models.Contact;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findAllByUserId(int userId);




}
