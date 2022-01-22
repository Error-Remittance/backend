package com.bank.api.account.dto.loadaccountresponsedto;

import java.util.ArrayList;

import lombok.Getter;

@Getter
public class LoadAccountList {
    Long GRID_CNT;
    ArrayList<com.bank.api.account.dto.loadaccountresponsedto.GRID> GRID = new ArrayList<com.bank.api.account.dto.loadaccountresponsedto.GRID>();
}
