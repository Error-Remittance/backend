package com.bank.component.account.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UpdateAllAccountRequestVo {
    private final String userId;
    private final String password;
}
