package br.com.fabex.api.service;

import br.com.fabex.api.model.Account;
import br.com.fabex.api.repository.AccountCrudRepository;
import br.com.fabex.api.repository.AccountTransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountTransferService {

    private final AccountCrudRepository accountCrudRepository;
    private final AccountTransferRepository accountTransferRepository;

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        Account sender = accountCrudRepository.findById(idSender).orElseThrow();
        Account receiver = accountCrudRepository.findById(idReceiver).orElseThrow();

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        accountTransferRepository.updateAmountById(idSender, senderNewAmount);
        if (receiver.getAmount().doubleValue() < 0) {
            /* if Exception is Checked, @Transaction not catch exception -> a big problem. */
            throw new RuntimeException("Receive Account is negative.");
        }
        accountTransferRepository.updateAmountById(idReceiver, receiverNewAmount);

    }
}
