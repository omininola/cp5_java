package br.com.fiap.cp5.socket;

import br.com.fiap.cp5.crypto.RSA;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Connection implements AutoCloseable {
    
    private final Socket socket;
    private final RSA rsa;
    private final HashMap<String, BigInteger> anotherPubKey = new HashMap<>();
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public Connection(Socket socket, RSA rsa) throws IOException {
        this.socket = socket;
        this.rsa = rsa;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        performHandshake();
    }

    private void performHandshake() throws IOException {
        HashMap<String, BigInteger> pubKey = this.rsa.getPublicKey();
        String toSend = "e=" + pubKey.get("e").toString() + ";n=" + pubKey.get("n").toString();
        writer.write(toSend);
        writer.newLine();
        writer.flush();

        String line = reader.readLine();
        
        for (String part : line.split(";")) {
            String[] kv = part.split("=");
            this.anotherPubKey.put(kv[0], new BigInteger(kv[1]));
        }
    }

    public void send(String message) throws IOException {
        List<BigInteger> encrypted = RSA.encrypt(message, anotherPubKey);
        StringBuilder sb = new StringBuilder();
        for (BigInteger bi : encrypted) {
            sb.append(bi.toString()).append(" ");
        }
        writer.write(sb.toString().trim());
        writer.newLine();
        writer.flush();
    }

    public String receive() throws IOException {
        String line = reader.readLine();
        if (line == null) return null;
        String[] parts = line.split(" ");
        List<BigInteger> encrypted = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty()) encrypted.add(new BigInteger(part));
        }
        return rsa.decrypt(encrypted);
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}