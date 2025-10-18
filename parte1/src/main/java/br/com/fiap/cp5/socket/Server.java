package br.com.fiap.cp5.socket;

import br.com.fiap.cp5.crypto.RSA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 6969;

    public static void main(String[] args) throws IOException {
        BigInteger p = BigInteger.valueOf(61);
        BigInteger q = BigInteger.valueOf(53);
        RSA rsa = new RSA(p, q);

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is running on port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());
            new Thread(new ClientHandler(clientSocket, rsa)).start();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;
        private final RSA rsa;

        ClientHandler(Socket socket, RSA rsa) {
            this.socket = socket;
            this.rsa = rsa;
        }

        public void run() {
            try (Connection connection = new Connection(socket, rsa);
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

                Thread receiveThread = new Thread(() -> {
                    try {
                        String msg;
                        while ((msg = connection.receive()) != null) {
                            System.out.println("Client says: " + msg);
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
            } catch (IOException e) {
                System.out.println("Client disconnected.");
            }
        }
    }
}