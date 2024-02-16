package br.com.fabex.api.integration.service;

import br.com.fabex.api.model.Account;
import br.com.fabex.api.repository.AccountRepository;
import br.com.fabex.api.service.AccountTransferService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;


@ActiveProfiles("test")
@SpringBootTest
@Order(4)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountTransferServiceIntegrationTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransferService accountTransferService;

    @Test
    @Order(1)
    @DisplayName("Transfer money with success.")
    public void transferMoneySucesso() {

        Account sender = new Account();
        sender.setId(1L);
        sender.setAmount(new BigDecimal(1000));

        Account receiver = new Account();
        receiver.setId(2L);
        receiver.setAmount(new BigDecimal(1000));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(receiver));

        accountTransferService.transferMoney(1L, 2L, new BigDecimal(100));

        verify(accountRepository).updateAmountById(1L, new BigDecimal(900));
        verify(accountRepository).updateAmountById(2L, new BigDecimal(1100));

    }

    @Test
    @Order(2)
    @DisplayName("Transfer money with failed: Receiver account is negative.")
    public void transferMoneyFailedReceiverNegative() {

        Account sender = new Account();
        sender.setId(1L);
        sender.setAmount(new BigDecimal(1000));

        Account receiver = new Account();
        receiver.setId(2L);
        receiver.setAmount(new BigDecimal(-1));

        given(accountRepository.findById(1L)).willReturn(Optional.of(sender));
        given(accountRepository.findById(2L)).willReturn(Optional.of(receiver));

        assertThrows(RuntimeException.class,
                () -> accountTransferService.transferMoney(1L, 2L, new BigDecimal(100)));

        verify(accountRepository).updateAmountById(1L, new BigDecimal(900));
        verify(accountRepository, never()).updateAmountById(2L, new BigDecimal(1100));

    }

    @Test
    @Order(3)
    @DisplayName("Transfer money with failed: Sender account not found.")
    public void transferMoneyFailedSenderNotFound() {
        given(accountRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> accountTransferService.transferMoney(1L, 2L, new BigDecimal(100)));

        verify(accountRepository, never()).updateAmountById(anyLong(), any());

    }

    @Test
    @Order(4)
    @DisplayName("Transfer money with failed: Receiver account not found.")
    public void transferMoneyFailedReceiverNotFound() {

        Account sender = new Account();
        sender.setId(1L);
        sender.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(1L)).willReturn(Optional.of(sender));
        given(accountRepository.findById(2L)).willReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> accountTransferService.transferMoney(1L, 2L, new BigDecimal(100)));

        verify(accountRepository, never()).updateAmountById(anyLong(), any());

    }
}
