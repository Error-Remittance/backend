package com.bank.component.account.vo;

import java.util.List;

import com.bank.component.transaction.vo.ReturnRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GetReceivedReturnRequestsResponseVo {
    private final List<ReturnRequestVo> receivedReturnRequestList;
}
