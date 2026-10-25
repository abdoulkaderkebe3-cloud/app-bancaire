package Kebe.application_bancaire.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
@Table(name = "utilisateurs")
public class User extends Mere implements UserDetails {



    private String firstName;



    private String lastName;


    @Column(unique = true)
    private String email;


    private String password;



    private String iban;

    private boolean active;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transaction;


    @OneToMany(mappedBy = "user")
    private List<Contact> contact;


    @OneToOne(mappedBy = "user")
    private Account account;


    @OneToOne(mappedBy = "user")
    private Address address;

    @OneToOne(mappedBy = "user")
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role != null ? role.getName() : "ROLE_USER"));

    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
