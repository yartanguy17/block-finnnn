package com.ebanking.blockchain.dto.responses;

import com.ebanking.blockchain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends CommonEntity implements Serializable {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String photoProfileUrl;
    private String photoProfileReference;
    private boolean neverConnected;
    private boolean statut;
    private boolean estConnecter;
    private Date lasteTimeConnected;
}
