package br.com.fabex.api.repository;

import br.com.fabex.api.model.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Query("UPDATE Account a SET a.amount = :amount WHERE a.id = :id")
    void updateAmountById(Long id, BigDecimal amount);

    List<Account> findAllByName(String name);

}
