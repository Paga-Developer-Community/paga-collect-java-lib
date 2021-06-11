package com.pagatech.collect.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class HistoryResponse extends Response {

    private int itemCount;

    private List<HistoryResponseItem> items;
}
