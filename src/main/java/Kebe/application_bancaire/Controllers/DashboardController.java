package Kebe.application_bancaire.Controllers;


import Kebe.application_bancaire.dto.TransactionSumDetails;
import Kebe.application_bancaire.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboards")
public class DashboardController {

    private final DashboardService service;


    @GetMapping("/sum-by-date/{user-id}")
    public ResponseEntity<List<TransactionSumDetails>> findSumTransactionsByDate(
            @PathVariable("user-id") Integer userId,
            @RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate startDate,
            @RequestParam("end-date") @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate endDate)
            {
        return ResponseEntity.ok(service.findSumTransactionByDate(startDate,endDate,userId));
    }

    @GetMapping("/account-balance/{user-id}")
    public ResponseEntity<BigDecimal> getAccountBalance(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.getAccountBalance(userId));
    }


    @GetMapping("/higest-transfert/{user-id}")
    public ResponseEntity <BigDecimal> highestTransfert(
            @PathVariable("user-id")  Integer userId
    ){
        return ResponseEntity.ok(service.highestTransfert(userId));
    }



    @GetMapping("/higestDeposit/{user-id}")
    public ResponseEntity<BigDecimal> highestDeposit(
            @PathVariable("user-id")   Integer userId
    ){
        return ResponseEntity.ok(service.highestDeposit(userId));
    }
}
