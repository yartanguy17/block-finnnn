package com.ebanking.blockchain.wallet;

import com.ebanking.blockchain.util.Base58Check;
import com.ebanking.blockchain.util.BtcAddressUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECParameterSpec;

@Data
@AllArgsConstructor
@Slf4j
public class Wallet implements Serializable {

    private static final long serialVersionUID = 166249065006236265L;

    private static final int ADDRESS_CHECKSUM_LEN = 4;

    private BCECPrivateKey privateKey;

    private byte[] publicKey;


    public Wallet() {
        initWallet();
    }

    private void initWallet() {
        try {
            KeyPair keyPair = newECKeyPair();
            BCECPrivateKey privateKey = (BCECPrivateKey) keyPair.getPrivate();
            BCECPublicKey publicKey = (BCECPublicKey) keyPair.getPublic();

            byte[] publicKeyBytes = publicKey.getQ().getEncoded(false);

            this.setPrivateKey(privateKey);
            this.setPublicKey(publicKeyBytes);
        } catch (Exception e) {
            log.error("Fail to init wallet ! ", e);
            throw new RuntimeException("Fail to init wallet ! ", e);
        }
    }


    private KeyPair newECKeyPair() throws Exception {

        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", BouncyCastleProvider.PROVIDER_NAME);
        // 椭圆曲线（EC）域参数设定
        // bitcoin 为什么会选择 secp256k1，详见：https://bitcointalk.org/index.php?topic=151120.0
        ECNamedCurveParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        keyPairGenerator.initialize(ecSpec, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }


    public String getAddress() {
        try {
            // 1. 获取 ripemdHashedKey
            byte[] ripemdHashedKey = BtcAddressUtils.ripeMD160Hash(this.getPublicKey());

            // 2. 添加版本 0x00
            ByteArrayOutputStream addrStream = new ByteArrayOutputStream();
            addrStream.write((byte) 0);
            addrStream.write(ripemdHashedKey);
            byte[] versionedPayload = addrStream.toByteArray();

            // 3. 计算校验码
            byte[] checksum = BtcAddressUtils.checksum(versionedPayload);

            // 4. 得到 version + paylod + checksum 的组合
            addrStream.write(checksum);
            byte[] binaryAddress = addrStream.toByteArray();

            // 5. 执行Base58转换处理
            return Base58Check.rawBytesToBase58(binaryAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Fail to get wallet address ! ");
    }
}
