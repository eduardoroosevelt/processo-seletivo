package br.com.eduardosilva.infrastructure.util;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public final class HashingUtils {

    private static final HashFunction CHECKSUM = Hashing.md5();

    private HashingUtils() {}

    public static String checksum(final byte[] content) {
        return CHECKSUM.hashBytes(content).toString();
    }
}
