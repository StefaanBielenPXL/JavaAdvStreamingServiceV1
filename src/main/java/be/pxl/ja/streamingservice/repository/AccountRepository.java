package be.pxl.ja.streamingservice.repository;

import be.pxl.ja.streamingservice.exception.DuplicateEmailException;
import be.pxl.ja.streamingservice.model.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        if (accounts.containsKey(account.getEmail())) {
            throw new DuplicateEmailException("Een account met hetzelfde email adres bestaat al.");
        }
        accounts.put(account.getEmail(), account);
    }

    public Account findAccount(String email) {
        //TODO als je de javadoc van de get methode van een HashMap bekijkt kan je volgende lezen "Returns: the value to which the specified key is mapped, or null if this map contains no mapping for the key"
        // Dit wil dus zeggen dat onderstaande code redundant is.
        if (accounts.containsKey(email)) {
            return accounts.get(email);
        }
        return null;
    }
}
