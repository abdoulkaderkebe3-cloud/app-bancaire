package Kebe.application_bancaire.services.impl;

import Kebe.application_bancaire.config.JwtUtils;
import Kebe.application_bancaire.dto.AccountDto;
import Kebe.application_bancaire.dto.AuthenticationResponse;
import Kebe.application_bancaire.dto.AuthenticationResquest;
import Kebe.application_bancaire.dto.UserDto;
import Kebe.application_bancaire.exceptions.ObjectsValidator;
import Kebe.application_bancaire.models.Role;
import Kebe.application_bancaire.models.User;
import Kebe.application_bancaire.repository.RoleRepository;
import Kebe.application_bancaire.repository.UserRepository;
import Kebe.application_bancaire.services.AccountService;
import Kebe.application_bancaire.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "ROLE_USER";

    private final AccountService accountService;

    private final UserRepository repository;

    private final ObjectsValidator<UserDto> validator;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authManager;

    private final RoleRepository roleRepository;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {

        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {

        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur n'existe pas" + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    @Override
    @Transactional
    public Integer validerCompte(Integer id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("aucun utilisateur trouver"));

        user.setActive(true);

        //creation compte bancaire
        if (user.getAccount() == null) {
            AccountDto accountDto = AccountDto.builder()
                    .user(UserDto.fromEntity(user))
                    .build();
            Integer accountId = accountService.save(accountDto);
            AccountDto savedAccount = accountService.findById(accountId);
            user.setIban(savedAccount.getIban());
        } else {
            user.setIban(user.getAccount().getIban());
        }

        repository.save(user);
        return user.getId();
    }

    @Override
    public Integer invaliderCompte(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("aucun utlisateur trouver"));

        user.setActive(false);
        repository.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public AuthenticationResponse register(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(
                findOrCreateRole(ROLE_USER)
        );
        var savedUser = repository.save(user);
        String token = jwtUtils.generateToken(savedUser);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationResquest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        final String token = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElse(null);
        if (role == null) {
            return roleRepository.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }

        return role;
    }
}
