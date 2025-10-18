package br.com.fiap.cp5.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Base64;

import br.com.fiap.cp5.crypto.RSA;
import br.com.fiap.cp5.crypto.RSAClient;
import br.com.fiap.cp5.crypto.RSAServer;

public class Connection {

    private static RSAServer server;
    private static RSAClient client;

    public Connection (RSAServer rsa) {
        Connection.server = rsa;
    }

    public Connection(RSAClient rsa) {
        Connection.client = rsa;
    }
    
    public static String receive(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        byte[] infoBytes = new byte[256];
        int readBytes = in.read();

        if (readBytes > 0) {
            return Base64.getEncoder().encodeToString(infoBytes);
        }

        return "";
    }

    public static void send(Socket socket, String text, RSA rsa) throws IOException {
        byte[] bytesMsg = Base64.getDecoder().decode(text);
        OutputStream out = socket.getOutputStream();
        if (rsa instanceof RSAClient) {
            out.write(server.encrypt("Oie, server!"));
        } else if (rsa instanceof RSAServer) {
            out.write(client.encrypt("Oie, client!"));
        }

    }
}
