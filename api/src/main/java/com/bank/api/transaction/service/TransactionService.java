package com.bank.api.transaction.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.component.account.repository.AccountRepository;
import com.bank.component.transaction.repository.TransactionRepository;
import com.bank.component.transaction.vo.GetTransactionListResponseVo;
import com.bank.component.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
	private final AccountRepository accountRepository;
	private final UserRepository userRepository;
	private final TransactionRepository transactionRepository;

	@Transactional(readOnly = true)
	public GetTransactionListResponseVo getTransactionListPerMonth(final long accountId, final int year,
		final int month)
		throws IOException {
		return null;
	}

	// @Transactional(readOnly = true)
	// public GetTransactionListResponseVo getTransactionListPerMonth(final long accountId, final int year,
	// 	final int month)
	// 	throws IOException {
	//
	// 	final Optional<Account> accountOptional = accountRepository.findTopById(accountId);
	//
	// 	final Account account = accountOptional.orElseThrow(() ->
	// 		new CommonException(ResponseCode.ACCOUNT_NOT_EXISTED));
	//
	// 	final List<Transaction> sentTransactionHistoryPerMonth = transactionRepository.findAllBySentAccountAndTimeOfOccurrenceBetweenOrderByTimeOfOccurrenceAsc(
	// 		account, LocalDateTime.of(year, month, 1, 0, 0, 0, 0),
	// 		LocalDateTime.of(year, month, 1, 0, 0, 0, 0).plusMonths(1));
	//
	// 	final List<Transaction> receivedTransactionHistoryPerMonth = transactionRepository.findAllByReceivedAccountAndTimeOfOccurrenceBetweenOrderByTimeOfOccurrenceAsc(
	// 		account, LocalDateTime.of(year, month, 1, 0, 0, 0, 0),
	// 		LocalDateTime.of(year, month, 1, 0, 0, 0, 0).plusMonths(1));
	//
	// 	final List<TransactionVo> transactionVoList = Transaction.toVoList(sentTransactionHistoryPerMonth,
	// 		receivedTransactionHistoryPerMonth);
	// 	return GetTransactionListResponseVo.builder().transactionList(transactionVoList).build();
	// }
}
