package com.bank.component.account.repository;

import com.bank.component.account.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

	// Optional<Account> findTopById(long id);
}
