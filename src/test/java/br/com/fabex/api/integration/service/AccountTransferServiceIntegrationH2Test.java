package br.com.fabex.api.integration.service;

import br.com.fabex.api.model.Account;
import br.com.fabex.api.repository.AccountRepository;
import br.com.fabex.api.service.AccountTransferService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;


@ActiveProfiles("test")
@SpringBootTest
@Order(5)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountTransferServiceIntegrationH2Test {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransferService accountTransferService;

    @Test
    @Order(1)
    @DisplayName("Transfer money with success.")
    public void transferMoneySucesso() {

        accountTransferService.transferMoney(1L, 2L, new BigDecimal(100));

        Optional<Account> optSender = accountRepository.findById(1L);
        Assertions.assertTrue(optSender.isPresent());
        Assertions.assertEquals(new BigDecimal(900).doubleValue(), optSender.get().getAmount().doubleValue());

        Optional<Account> optReceiver = accountRepository.findById(2L);
        Assertions.assertTrue(optReceiver.isPresent());
        Assertions.assertEquals(new BigDecimal(1100).doubleValue(), optReceiver.get().getAmount().doubleValue());

    }


}
