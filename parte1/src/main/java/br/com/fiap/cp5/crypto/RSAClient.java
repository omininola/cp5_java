package br.com.fiap.cp5.crypto;

import java.math.BigInteger;

public class RSAClient extends RSA {
    public RSAClient(BigInteger p, BigInteger q) {
        super(p, q);
    }
}
