package Kebe.application_bancaire.services;


import Kebe.application_bancaire.dto.TransactionSumDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DashboardService  {

    List<TransactionSumDetails> findSumTransactionByDate(LocalDate startDate, LocalDate endDate, int userId );

    BigDecimal getAccountBalance(Integer userId);

    BigDecimal highestTransfert(Integer userId);

    BigDecimal highestDeposit(Integer userId);



}
