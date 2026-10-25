package Kebe.application_bancaire.services;

import Kebe.application_bancaire.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto> {

    List<ContactDto>findAllByUserId(Integer userId);
}
