package Kebe.application_bancaire.services.impl;

import Kebe.application_bancaire.dto.TransactionSumDetails;
import Kebe.application_bancaire.models.TransactionType;
import Kebe.application_bancaire.repository.TransactionRepository;
import Kebe.application_bancaire.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    public final TransactionRepository transactionRepository;


    @Override
    public List<TransactionSumDetails> findSumTransactionByDate(LocalDate startDate, LocalDate endDate, int userId) {

        LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23,59,59));
        return transactionRepository.findSumTransactionsByDate(start,end,userId);
    }

    @Override
    public BigDecimal getAccountBalance(Integer userId) {
        return transactionRepository.findAccountBalance(userId);
    }

    @Override
    public BigDecimal highestTransfert(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.TRANSFERT);
    }

    @Override
    public BigDecimal highestDeposit(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId,TransactionType.DEPOSIT);
    }
}
