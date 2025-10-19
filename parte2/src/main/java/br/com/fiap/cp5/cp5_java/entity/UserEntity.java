package br.com.fiap.cp5.cp5_java.entity;

import br.com.fiap.cp5.cp5_java.entity.enuns.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_USERS_CP5")

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserEntity {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user")
    @SequenceGenerator(name = "user", sequenceName = "SQ_T_USERS_CP5", allocationSize = 1)
    private Long idUser;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}