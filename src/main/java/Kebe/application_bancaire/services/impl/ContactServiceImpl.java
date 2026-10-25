package Kebe.application_bancaire.services.impl;

import Kebe.application_bancaire.dto.ContactDto;
import Kebe.application_bancaire.exceptions.ObjectsValidator;
import Kebe.application_bancaire.models.Contact;
import Kebe.application_bancaire.models.User;
import Kebe.application_bancaire.repository.ContactRepository;
import Kebe.application_bancaire.services.ContactService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService{

    private final ContactRepository repository;


    private final ObjectsValidator<ContactDto> validator;


    @Override
    public Integer save(ContactDto dto) {
       validator.validate(dto);
       Contact contact = ContactDto.toEntity(dto);
       return repository.save(contact).getId();

    }

    @Override
    public List<ContactDto> findAll() {
        return repository.findAll()
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return repository.findById(id)
                .map(ContactDto::fromEntity)
                .orElseThrow( ()-> new EntityNotFoundException("contact inexistent "));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    @Override
    public List<ContactDto>findAllByUserId(Integer userId) {
        return repository.findById(userId)
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }


}
