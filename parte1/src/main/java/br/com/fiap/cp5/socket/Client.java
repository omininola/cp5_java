package br.com.fiap.cp5.socket;

import java.math.BigInteger;
import java.net.Socket;

import br.com.fiap.cp5.crypto.RSAClient;

public class Client {
    Socket socket;

    public void sendMessages() throws Exception {

        socket = new Socket("localhost", 6969);

        BigInteger p = BigInteger.valueOf(37);
        BigInteger q = BigInteger.valueOf(47);
        RSAClient rsa = new RSAClient(p, q);

        Connection conn = new Connection(rsa);
    }
}
