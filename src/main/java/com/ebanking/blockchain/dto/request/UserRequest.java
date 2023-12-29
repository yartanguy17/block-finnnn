package com.ebanking.blockchain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {
    private String name;
    private String username;
    private String password;
    private String email;
}
