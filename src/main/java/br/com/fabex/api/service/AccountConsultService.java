package br.com.fabex.api.service;

import br.com.fabex.api.model.Account;
import br.com.fabex.api.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountConsultService {
    private final AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> getAllAccountsByName(String name) {
        return accountRepository.findAllByName(name);
    }

}
