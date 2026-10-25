package Kebe.application_bancaire.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
@Data
@EntityListeners(AuditingEntityListener.class)
public class Mere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @CreatedDate
    @Column(
            name = "date de creation",
            updatable = false,
            nullable=false
    )
    private LocalDateTime creationDate;


     @LastModifiedDate
     @Column(name = "date de mise a jour")
    private LocalDateTime lastupdate;
}
