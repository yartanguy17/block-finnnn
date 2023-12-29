package com.ebanking.blockchain.util;

import org.apache.maven.surefire.shared.lang3.ArrayUtils;
import org.springframework.security.crypto.codec.Hex;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Stream;

public class ByteUtils {

    public static final byte[] EMPTY_ARRAY = new byte[0];

    public static final byte[] EMPTY_BYTES = new byte[32];

    public static final char[] ZERO_HASH = Hex.encode(EMPTY_BYTES);


    public static byte[] merge(byte[]... bytes) {
        Stream<Byte> stream = Stream.of();
        for (byte[] b: bytes) {
            stream = Stream.concat(stream, Arrays.stream(ArrayUtils.toObject(b)));
        }
        return ArrayUtils.toPrimitive(stream.toArray(Byte[]::new));
    }

    public static byte[] toBytes(long val) {
        return ByteBuffer.allocate(Long.BYTES).putLong(val).array();
    }
}
