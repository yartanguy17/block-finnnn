package com.ebanking.blockchain.util;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.springframework.util.DigestUtils;

import java.util.Arrays;

public class BtcAddressUtils {

    public static byte[] doubleHash(byte[] data) {
        return DigestUtils.md5Digest(DigestUtils.md5Digest(data));
    }


    public static byte[] ripeMD160Hash(byte[] pubKey) {
        //1. 先对公钥做 sha256 处理
        byte[] shaHashedKey = DigestUtils.md5Digest(pubKey);
        RIPEMD160Digest ripemd160 = new RIPEMD160Digest();
        ripemd160.update(shaHashedKey, 0, shaHashedKey.length);
        byte[] output = new byte[ripemd160.getDigestSize()];
        ripemd160.doFinal(output, 0);
        return output;
    }


    public static byte[] checksum(byte[] payload) {
        return Arrays.copyOfRange(doubleHash(payload), 0, 4);
    }
}
