package com.bank.component.transaction.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ReturnRequestVo {

    private Long id;
    private String sentUserName;
    private String sentAccountNumber;
    private String receivedUserName;
    private String receivedAccountNumber;
    private String message;
    private String reason;
    private LocalDateTime transactionTime;
    private boolean isReported;
    private boolean isConcluded;
    private double amount;

}
