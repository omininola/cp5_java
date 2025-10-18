package br.com.fiap.cp5.crypto;

import java.math.BigInteger;

public class RSAServer extends RSA {
    public RSAServer(BigInteger p, BigInteger q) {
        super(p, q);
    }
}
