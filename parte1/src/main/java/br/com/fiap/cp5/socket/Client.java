package br.com.fiap.cp5.socket;

import br.com.fiap.cp5.crypto.RSA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.Socket;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 6969;

    public static void main(String[] args) throws IOException {
        BigInteger p = BigInteger.valueOf(37);
        BigInteger q = BigInteger.valueOf(47);
        RSA rsa = new RSA(p, q);

        try (Socket socket = new Socket(HOST, PORT);
            Connection connection = new Connection(socket, rsa);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Connected to server.");

            Thread receiveThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = connection.receive()) != null) {
                        System.out.println("Server says: " + msg);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            });
            
            receiveThread.start();

            String line;
            while ((line = console.readLine()) != null) {
                connection.send(line);
            }
        }
    }
}