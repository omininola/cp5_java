package br.com.fiap.cp4.cp4_java.exception;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public static Supplier<NotFoundException> forUser(Long id) {
        return () -> new NotFoundException("Usuario não achado para id" + id);
    }

    public static Supplier<NotFoundException> forLogin() {
        return () -> new NotFoundException("Usuário não encontrado na base dados");
    }


}
