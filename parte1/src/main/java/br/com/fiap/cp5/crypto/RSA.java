package br.com.fiap.cp5.crypto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RSA {
    
    private final BigInteger p;
    private final BigInteger q;
    private final BigInteger n;
    private final BigInteger phi;
    private final BigInteger e;
    private final BigInteger d;

    public RSA(BigInteger p, BigInteger q) {
        if (!isPrime(p) || !isPrime(q)) {
            throw new IllegalArgumentException("P ou Q precisam ser números primos");
        }

        if (p == q) {
            throw new IllegalArgumentException("P e Q precisam ser diferentes");
        }

        this.p = p;
        this.q = q;
        this.n = p.multiply(q);
        this.phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        this.e = calcPublicExponent(this.phi);
        this.d = calcPrivateExponent(this.e, this.phi);
    }

    private BigInteger calcPublicExponent(BigInteger phi) {
        BigInteger publicExponent = BigInteger.ZERO;
        
        while(phi.gcd(e).intValue() > 1) {
            publicExponent = publicExponent.add(BigInteger.TWO);
        }

        return publicExponent;
    }

    private BigInteger calcPrivateExponent(BigInteger e, BigInteger phi) {
        return e.modInverse(phi);
    }

    private boolean isPrime(BigInteger number) {
        if (number.longValue() <= 1) return false;

        for (long i = 2; i * i <= number.longValue(); i++) {
            if (number.longValue() % i == 0) {
                return false;
            }
        }

        return true;
    }

    public List<BigInteger> encrypt(String msg) {
        List<BigInteger> encrypted = new ArrayList<BigInteger>();

        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            BigInteger m = BigInteger.valueOf(c);
            BigInteger cipherValue = m.modPow(this.e, this.n);
            encrypted.add(cipherValue);
        }

        return encrypted;
    }

    public String decrypt(List<BigInteger> msg) {
        StringBuilder decrypted = new StringBuilder();

        for (BigInteger c : msg) {
            BigInteger m = c.modPow(this.d, this.n);
            char character = (char) m.longValue();
            decrypted.append(character);
        }

        return decrypted.toString();
    }

}
