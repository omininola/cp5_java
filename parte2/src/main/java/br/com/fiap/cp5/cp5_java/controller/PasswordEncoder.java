package br.com.fiap.cp5.cp5_java.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        // Senha que queremos hashear
        String rawPassword = "admin123";

        // Cria o encoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Gera o hash
        String hashedPassword = encoder.encode(rawPassword);

        // Imprime o hash (será algo como $2a$10$..................................)
        System.out.println("O Hash para 'admin123' é: " + hashedPassword);
    }
}