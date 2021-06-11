package com.pagatech.collect.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class BanksResponse extends Response {

    List<Bank> banks;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    public static class Bank implements Serializable {
        private String name;
        private String uuid;
        private String interInstitutionCode;
    }
}