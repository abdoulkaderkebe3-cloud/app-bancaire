package Kebe.application_bancaire.services;

import Kebe.application_bancaire.dto.AuthenticationResponse;
import Kebe.application_bancaire.dto.AuthenticationResquest;
import Kebe.application_bancaire.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{


    Integer validerCompte(Integer id);

    Integer invaliderCompte(Integer id);

    AuthenticationResponse register(UserDto user);

    AuthenticationResponse authenticate(AuthenticationResquest request);
}
