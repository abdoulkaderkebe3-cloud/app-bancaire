package Kebe.application_bancaire.services.impl;

import Kebe.application_bancaire.dto.AddressDto;
import Kebe.application_bancaire.exceptions.ObjectsValidator;
import Kebe.application_bancaire.models.Address;
import Kebe.application_bancaire.models.User;
import Kebe.application_bancaire.repository.AddressRepository;
import Kebe.application_bancaire.services.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository repository;

    private final ObjectsValidator<AddressDto> validator;



    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return repository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {

        return repository.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {

        return repository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(() -> new  EntityNotFoundException("aucune address trouver a l'id"+id));
    }

    @Override
    public void delete(Integer id) {

        repository.deleteById(id);
    }
}


