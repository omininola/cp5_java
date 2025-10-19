package br.com.fiap.cp4.cp4_java.entity.enuns;

public enum RoleEnum {
    ADMIN("Admin"),
    USER("User");

    private final String descricao;

    RoleEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}