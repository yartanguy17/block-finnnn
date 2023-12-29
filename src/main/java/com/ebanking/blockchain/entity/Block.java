package com.ebanking.blockchain.entity;

import com.ebanking.blockchain.common.CommonEntity;
import com.google.common.hash.Hashing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.maven.surefire.shared.lang3.builder.HashCodeBuilder;
import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "block")
public class Block extends CommonEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "index", nullable = false)
    private int index;
    @Column(name = "hash", nullable = false)
    private  String hash;
    @Column(name = "previous_hash", nullable = true)
    private  String previousHash;
    @Column(name = "data", nullable = false)
    private  String data;
    @Column(name = "difficult", nullable = true)
    private int difficult;
    @Column(name = "nonce", nullable = true)
    private int nonce;

    private static final String HASH_ALGORITHM = "sha256";

    public Block( int index, String hash, String data) {
    }
    public static Block genesis() {
        return new Block(0, "8b31c9ec8c2df21968aca3edd2bda8fc77ed45b0b3bc8bc39fa27d5c795bc829", "PHP is awesome!");
    }
    public Boolean isNextValid(Block block) {
        if (block.getIndex() != this.index + 1) {
            return  false;
        }

        if (block.getPreviousHash() != this.hash) {
            return  false;
        }
        if (block.hash != calculateHash(this.getIndex(),this.getPreviousHash(),this.getData(),this.getDifficult(),this.getNonce())){
            return false;
        }
        return true;
    }
    public Boolean isEqual(Block block){
        return block.isEqual(this);
    }
    public static String calculateHash(int index, String previousHash,String data, int difficult, int nonce) {
        String sha256hex = Hashing.sha256()
                .hashString(HASH_ALGORITHM+index+previousHash+data+difficult+nonce, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }

}
