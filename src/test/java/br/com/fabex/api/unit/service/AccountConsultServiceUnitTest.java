package br.com.fabex.api.unit.service;

import br.com.fabex.api.model.Account;
import br.com.fabex.api.repository.AccountRepository;
import br.com.fabex.api.service.AccountConsultService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@Order(2)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class AccountConsultServiceUnitTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountConsultService accountConsultService;

    @Test
    @DisplayName("Find all account with success: List not empty.")
    public void findAllSucessNotEmpty() {
        List<Account> listExpected = this.getAllAccountMock();
        given(accountRepository.findAll()).willReturn(listExpected);
        List<Account> allAccounts = accountConsultService.getAllAccounts();
        assertEquals(listExpected, allAccounts);
    }

    @Test
    @DisplayName("Find all account with success: List empty.")
    public void findAllSucessEmpty() {
        List<Account> listExpected = Collections.emptyList();
        given(accountRepository.findAll()).willReturn(listExpected);
        List<Account> allAccounts = accountConsultService.getAllAccounts();
        assertEquals(listExpected, allAccounts);
    }

    @Test
    @DisplayName("Find all account by name with success: Name not empty.")
    public void findAllByNameSucessNameNotEmpty() {
        String name = "Fabex";
        List<Account> listExpected = this.getAllAccountMock()
                .stream()
                .filter(account -> account.getName().equals(name))
                .toList();
        given(accountRepository.findAllByName(name)).willReturn(listExpected);
        List<Account> allAccounts = accountConsultService.getAllAccountsByName(name);
        assertEquals(listExpected, allAccounts);
    }

    @Test
    @DisplayName("Find all account by name with success: Name empty.")
    public void findAllByNameSucessNameEmpty() {
        String name = "";
        List<Account> listExpected = this.getAllAccountMock()
                .stream()
                .filter(account -> account.getName().equals(name))
                .toList();
        given(accountRepository.findAllByName(name)).willReturn(listExpected);
        List<Account> allAccounts = accountConsultService.getAllAccountsByName(name);
        assertEquals(listExpected, allAccounts);
    }

    @Test
    @DisplayName("Find all account by name with success: Name is Null.")
    public void findAllByNameSucessNameIsNull() {
        List<Account> listExpected = Collections.emptyList();
        given(accountRepository.findAllByName(null)).willReturn(listExpected);
        List<Account> allAccounts = accountConsultService.getAllAccountsByName(null);
        assertEquals(listExpected, allAccounts);
    }

    public List<Account> getAllAccountMock() {
        return List.of(new Account[]{
                new Account(1L, "Fabex", new BigDecimal(1000)),
                new Account(2L, "Helen", new BigDecimal(1000)),
                new Account(3L, "Peter", new BigDecimal(1000))
        });
    }

}
