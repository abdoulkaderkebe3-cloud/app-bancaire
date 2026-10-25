package Kebe.application_bancaire.services;

import Kebe.application_bancaire.dto.TransactionDto;
import Kebe.application_bancaire.models.Transaction;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto> {

    List<TransactionDto> findAllByUserId(Integer userId);

}
