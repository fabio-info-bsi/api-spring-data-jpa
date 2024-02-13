package br.com.fabex.api.controller;

import br.com.fabex.api.dto.TransferRequest;
import br.com.fabex.api.model.Account;
import br.com.fabex.api.service.AccountConsultService;
import br.com.fabex.api.service.AccountTransferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private AccountTransferService accountTransferService;

    private AccountConsultService accountConsultService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountConsultService.getAllAccounts();
    }

    @GetMapping("/name")
    public List<Account> getAllAccountsByName(@RequestParam(required = false) String name) {
        return accountConsultService.getAllAccountsByName(name);
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest request) {
        accountTransferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount());
    }
}
