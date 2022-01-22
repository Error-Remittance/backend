package com.bank.component.account.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UpdateAllAccountResponseVo {
    List<AccountVo> accountList;
}
