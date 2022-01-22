package com.bank.component.transaction.vo;

import java.util.List;

import com.bank.component.transaction.vo.TransactionVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GetTransactionListResponseVo {
    List<TransactionVo> transactionList;
}
