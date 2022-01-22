package com.bank.component.transaction.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class TransactionVo {

    private Long id;
    private String infoTarget;
    private LocalDateTime timeOfOccurrence;
    private String senderName;
    private String receiverName;
    private String sendingMethod;
    private String receivingMethod;
    private double amount;
    private Long sentAccountId;
    private Long receivedAccountId;
    private Boolean isreturnRequested;

}
