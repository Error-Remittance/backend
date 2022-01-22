package com.bank.component.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.component.account.domain.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

	Optional<Bank> findTopByName(String name);
}
