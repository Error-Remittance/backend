package com.bank.component.transaction.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bank.component.account.domain.Account;

@Getter
@NoArgsConstructor
@Entity
public class Transaction {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transaction_id")
	private Long id;

	// tran_date + tran_time을 합치는 메서드 작성해야함
	private LocalDateTime timeOfOccurrence;
	private String inputType;
	private String tranType;
	private String printContent;
	private int tranAmt;
	private int afterBalanceAmt;
	private int branchName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private Account account;

	/**
	 * 양방향 연관관계 주입 메서드 제공해야함
	 */
	public void setAccount(Account account){
		this.account = account;
		account.getTransactions().add(this);
	}

	// public Transaction(LocalDateTime timeOfOccurrence, String senderName, String receiverName, String sendingMethod,
	// 	String receivingMethod, double amount, Account sentAccount, Account receivedAccount) {
	//
	// }

	// public boolean isRequested() {
	// 	return returnRequest != null;
	// }

	// public TransactionVo toVo(String infoTarget) {
	// 	return TransactionVo.builder()
	// 		.id(id)
	//
	// 		.build();
	// }

	// public static List<TransactionVo> toVoList(List<Transaction> sentTransactionHistory,
	// 	List<Transaction> receivedTransactionHistory) {
	//
	// 	final List<TransactionVo> sentTransactionVoList = sentTransactionHistory.stream()
	// 		.map((transaction) -> transaction.toVo("sender"))
	// 		.collect(Collectors.toList());
	//
	// 	final List<TransactionVo> receivedTransactionVoList = receivedTransactionHistory.stream()
	// 		.map((transaction) -> transaction.toVo("receiver"))
	// 		.collect(Collectors.toList());
	//
	// 	List<TransactionVo> totalTransactionVoList = new ArrayList<>();
	//
	// 	totalTransactionVoList.addAll(sentTransactionVoList);
	// 	totalTransactionVoList.addAll(receivedTransactionVoList);
	//
	// 	totalTransactionVoList.sort(Comparator.comparing(TransactionVo::getTimeOfOccurrence));
	//
	// 	return totalTransactionVoList;
	// }
}
