package com.ebanking.blockchain.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDTO implements Serializable {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private boolean neverConnected;
    private boolean estConnecter;
    private Date lasteTimeConnected;
}
