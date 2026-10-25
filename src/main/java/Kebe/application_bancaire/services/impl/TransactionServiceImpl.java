package Kebe.application_bancaire.services.impl;

import Kebe.application_bancaire.dto.TransactionDto;
import Kebe.application_bancaire.exceptions.ObjectsValidator;
import Kebe.application_bancaire.models.Transaction;
import Kebe.application_bancaire.models.TransactionType;
import Kebe.application_bancaire.models.User;
import Kebe.application_bancaire.repository.TransactionRepository;
import Kebe.application_bancaire.services.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    private final ObjectsValidator<TransactionDto> validator;


    @Override
    public Integer save(TransactionDto dto) {
        validator. validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        BigDecimal transactionMultiplier = BigDecimal.valueOf(transactionTypE(transaction.getType()
        ));
        BigDecimal amount =transaction.getAmount().multiply(transactionMultiplier);
        transaction.setAmount(amount);
        return repository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return repository.findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return repository.findById(id)
                .map(TransactionDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("aucune transaction trouver"));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    private int transactionTypE(TransactionType type){
        return TransactionType.TRANSFERT == type ? -1 : 1;
    }


    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }
}

