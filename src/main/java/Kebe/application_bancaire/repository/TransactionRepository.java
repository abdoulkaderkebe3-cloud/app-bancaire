package Kebe.application_bancaire.repository;

import Kebe.application_bancaire.dto.TransactionSumDetails;
import Kebe.application_bancaire.models.Transaction;
import Kebe.application_bancaire.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {


     @Query("select max(abs(t.amount)) as amount from Transaction t where t.user.id = :userId and t.type= :transactionType ")
     BigDecimal findHighestAmountByTransactionType(int userId, TransactionType transactionType);

    List<Transaction> findAllByUserId(Integer userId);


    @Query("select sum(t.amount) from Transaction t where t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId") int userId);


    @Query("select t.transactionDate as transactionDate, sum(t.amount) as amount from Transaction t where t.user.id = :userId and t.creationDate between :startDate and :endDate group by t.transactionDate")
    List<TransactionSumDetails> findSumTransactionsByDate(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("userId") Integer userId
    );
}
