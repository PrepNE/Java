package com.mikepn.bankingsystem.v1.controller;

import com.mikepn.bankingsystem.v1.dto.request.transaction.CreateTransactionDTO;
import com.mikepn.bankingsystem.v1.dto.request.transaction.DepositOrWithDrawDTO;
import com.mikepn.bankingsystem.v1.dto.response.auth.Response;
import com.mikepn.bankingsystem.v1.models.Account;
import com.mikepn.bankingsystem.v1.models.Transaction;
import com.mikepn.bankingsystem.v1.payload.ApiResponse;
import com.mikepn.bankingsystem.v1.services.ITransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("transaction")
@RequiredArgsConstructor
@Tag(name = "Transaction")
public class TransactionController {
    private final ITransactionService transactionService;



    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse<Transaction>> deposit(@Valid  @RequestBody DepositOrWithDrawDTO dto) {
        try{
            Transaction transaction = transactionService.deposit(dto);
            return ApiResponse.success("Deposit successful", HttpStatus.OK, transaction);
        }catch (Exception e){
            return ApiResponse.fail("Deposit Failed",BAD_REQUEST,e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<Transaction>> withdraw(@Valid  @RequestBody DepositOrWithDrawDTO dto) {
        try{
            Transaction transaction = transactionService.withdraw(dto);
            return ApiResponse.success("Withdraw successful", HttpStatus.OK, transaction);
        }catch (Exception e){
            return ApiResponse.fail("Withdraw Failed",BAD_REQUEST,e.getMessage());
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse<Transaction>> transfer(@Valid  @RequestBody CreateTransactionDTO dto) {
        try{
            Transaction transaction = transactionService.transfer(dto);
            return ApiResponse.success("Transfer successful", HttpStatus.OK, transaction);
        }catch (Exception e){
            return ApiResponse.fail("Transfer Failed",BAD_REQUEST,e.getMessage());
        }
    }


    @GetMapping("/my-account")
    public ResponseEntity<ApiResponse<Account>> getMyAccount() {
        try{
            Account account = transactionService.getMyAccount();
            return ApiResponse.success("Account successful", HttpStatus.OK, account);
        }catch (Exception e){
            return ApiResponse.fail("Get My Account Failed",BAD_REQUEST,e.getMessage());
        }
    }
}
