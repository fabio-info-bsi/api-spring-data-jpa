package br.com.fabex.api.repository;

import br.com.fabex.api.model.Account;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.math.BigDecimal;

public interface AccountTransferRepository extends Repository<Account, Long> {

    @Modifying
    @Query("UPDATE account SET amount = :amount WHERE id = :id")
    void updateAmountById(long id, BigDecimal amount);

}
