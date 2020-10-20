package be.pxl.ja.streamingservice.repository;

import be.pxl.ja.streamingservice.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountRepositoryTest {
    private AccountRepository accountRepository;
    @BeforeEach
    public void init() {
        accountRepository = new AccountRepository();
    }

    @Test
    public void shouldAddAccountToAccountsMap() {
        // Arrange
        Account account = new Account("student@pxl.be", "profile1");

        // Act
        accountRepository.addAccount(account);

        // Assert
        Assertions.assertEquals(accountRepository.findAccount(account.getEmail()), account);
    }
}
