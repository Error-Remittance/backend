package com.bank.component.transaction.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class MakeReturnRequestRequestVo {
    private long transactionId;
    private String message;
    private String reason;
}
