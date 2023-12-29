package com.ebanking.blockchain.entity;

import com.ebanking.blockchain.common.CommonEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_user")
public class User extends CommonEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @NotBlank
    @Size(min = 3, max = 60)
    @Column(unique = true)
    private String username;
    @NaturalId
    @NotBlank
    @Size(max = 80)
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
    @Column(name = "never_connected", columnDefinition = "boolean default true")
    private boolean neverConnected;
    @Column(name = "est_connecter", columnDefinition = "boolean default false")
    private boolean estConnecter;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lasteTimeConnected;
}
