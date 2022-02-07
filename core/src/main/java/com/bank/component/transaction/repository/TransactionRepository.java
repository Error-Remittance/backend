package com.bank.component.transaction.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.component.account.domain.Account;
import com.bank.component.transaction.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	// List<Transaction> findAllBySentAccountAndTimeOfOccurrenceBetweenOrderByTimeOfOccurrenceAsc(
	// 	Account sentAccount, LocalDateTime startTimeOfOccurrence, LocalDateTime endTimeOfOccurrence);
	//
	// List<Transaction> findAllByReceivedAccountAndTimeOfOccurrenceBetweenOrderByTimeOfOccurrenceAsc(
	// 	Account receivedAccount, LocalDateTime startTimeOfOccurrence, LocalDateTime endTimeOfOccurrence);
	//
	// Optional<Transaction> findTopById(Long id);
}
