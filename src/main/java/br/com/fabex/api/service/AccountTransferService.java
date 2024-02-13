package br.com.fabex.api.service;

import br.com.fabex.api.model.Account;
import br.com.fabex.api.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountTransferService {

    private final AccountRepository accountRepository;

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        Account sender = accountRepository.findById(idSender).orElseThrow();
        Account receiver = accountRepository.findById(idReceiver).orElseThrow();

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        accountRepository.updateAmountById(idSender, senderNewAmount);
        if (receiver.getAmount().doubleValue() < 0) {
            /* if Exception is Checked, @Transaction not catch exception -> a big problem. */
            throw new RuntimeException("Receive Account is negative.");
        }
        accountRepository.updateAmountById(idReceiver, receiverNewAmount);

    }
}
