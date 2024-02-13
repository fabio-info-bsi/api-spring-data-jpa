package br.com.fabex.api.service;

import br.com.fabex.api.model.Account;
import br.com.fabex.api.repository.AccountCrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountConsultService {
    private final AccountCrudRepository accountCrudRepository;

    public List<Account> getAllAccounts() {
        return accountCrudRepository.findAll();
    }

    public List<Account> getAllAccountsByName(String name) {
        return accountCrudRepository.findAllByName(name);
    }

}
