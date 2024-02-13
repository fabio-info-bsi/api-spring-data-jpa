package br.com.fabex.api.repository;

import br.com.fabex.api.model.Account;
import jakarta.annotation.Nonnull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountCrudRepository extends CrudRepository<Account, Long> {

    @Nonnull
    List<Account> findAll();

    List<Account> findAllByName(String name);
}
