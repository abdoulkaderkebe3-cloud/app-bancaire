package Kebe.application_bancaire.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@SuperBuilder
@AllArgsConstructor
public class Transaction  extends  Mere{




    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String destinationIban;


    @Column(updatable = false)
    private LocalDate transactionDate;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
