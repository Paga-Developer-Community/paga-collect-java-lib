package com.pagatech.collect.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class HistoryRequest {

    private String referenceNumber;


    private String startDateTimeUTC;

    private String endDateTimeUTC;
}
