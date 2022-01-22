package com.bank.component.account.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class AccountVo {
    private Long id;
    private String bank;
    private String number;
    private String name;
    private double balance;
}
