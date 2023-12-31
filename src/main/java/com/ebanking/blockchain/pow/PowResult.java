package com.ebanking.blockchain.pow;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowResult {
    private long nonce;
    private String hash;
}
