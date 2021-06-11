package com.pagatech.collect.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
class Request {

    @NonNull
    private String referenceNumber;

}
