package com.ebanking.blockchain.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private String accessToken;
    private String type = "Bearer";
}
