package Kebe.application_bancaire.dto;

import Kebe.application_bancaire.models.Transaction;
import Kebe.application_bancaire.models.TransactionType;
import Kebe.application_bancaire.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TransactionDto {

    private Integer id;

    @Positive
    private BigDecimal amount;

    private TransactionType type;

    private String destinationIban;

    private LocalDate transactionDate;


    private Integer userId;


    public static TransactionDto fromEntity(Transaction transaction){
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .transactionDate(transaction.getTransactionDate())
                .userId((transaction.getUser().getId()))
                .build();
    }

    public static Transaction toEntity(TransactionDto transaction){
        return Transaction.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .transactionDate(LocalDate.now())
                .user(
                        User.builder()
                                .id(transaction.getUserId())
                                .build()
                )
                .build();
    }

}
