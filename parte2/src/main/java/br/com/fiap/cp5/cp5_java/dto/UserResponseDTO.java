package br.com.fiap.cp5.cp5_java.dto;

import br.com.fiap.cp5.cp5_java.entity.UserEntity;
import br.com.fiap.cp5.cp5_java.entity.enuns.RoleEnum;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserResponseDTO {

    private Long idUser;
    private String name;
    private String email;
    private String password;
    private RoleEnum role;

    public static UserResponseDTO from(UserEntity u) {
        return UserResponseDTO.builder()
                .idUser(u.getIdUser())
                .name(u.getName())
                .name(u.getName())
                .email(u.getEmail())
                .password(u.getPassword())
                .role(u.getRole())
                .build();
    }

}
