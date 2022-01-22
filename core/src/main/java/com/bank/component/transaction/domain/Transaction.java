package com.bank.component.transaction.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bank.component.account.domain.Account;
import com.bank.component.transaction.vo.TransactionVo;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "transaction")
public class Transaction {

	@Id @GeneratedValue
	@Column(name = "transaction_id")
	private Long id;

	@Column(name = "time_of_occurrence")
	private LocalDateTime timeOfOccurrence;

	@Column(name = "sender_name")
	private String senderName;

	@Column(name = "receiver_name")
	private String receiverName;

	@Column(name = "sending_method")
	private String sendingMethod;

	@Column(name = "receivingMethod")
	private String receivingMethod;

	@Column(name = "amount")
	private double amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Account sentAccount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Account receivedAccount;

	@OneToOne(mappedBy = "transaction")
	private ReturnRequest returnRequest;

	public Transaction(LocalDateTime timeOfOccurrence, String senderName, String receiverName, String sendingMethod,
		String receivingMethod, double amount, Account sentAccount, Account receivedAccount) {
		this.timeOfOccurrence = timeOfOccurrence;
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.sendingMethod = sendingMethod;
		this.receivingMethod = receivingMethod;
		this.amount = amount;
		this.sentAccount = sentAccount;
		this.receivedAccount = receivedAccount;
	}

	public boolean isRequested() {
		return returnRequest != null;
	}

	public TransactionVo toVo(String infoTarget) {
		return TransactionVo.builder()
			.id(id)
			.infoTarget(infoTarget)
			.timeOfOccurrence(timeOfOccurrence)
			.senderName(senderName)
			.receiverName(receiverName)
			.sendingMethod(sendingMethod)
			.receivingMethod(receivingMethod)
			.amount(amount)
			.sentAccountId(sentAccount.getId())
			.receivedAccountId(receivedAccount.getId())
			.isreturnRequested(isRequested())
			.build();
	}

	public static List<TransactionVo> toVoList(List<Transaction> sentTransactionHistory,
		List<Transaction> receivedTransactionHistory) {

		final List<TransactionVo> sentTransactionVoList = sentTransactionHistory.stream()
			.map((transaction) -> transaction.toVo("sender"))
			.collect(Collectors.toList());

		final List<TransactionVo> receivedTransactionVoList = receivedTransactionHistory.stream()
			.map((transaction) -> transaction.toVo("receiver"))
			.collect(Collectors.toList());

		List<TransactionVo> totalTransactionVoList = new ArrayList<>();

		totalTransactionVoList.addAll(sentTransactionVoList);
		totalTransactionVoList.addAll(receivedTransactionVoList);

		totalTransactionVoList.sort(Comparator.comparing(TransactionVo::getTimeOfOccurrence));

		return totalTransactionVoList;
	}
}
