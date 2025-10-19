package br.com.fiap.cp5.cp5_java.service;

import br.com.fiap.cp5.cp5_java.dto.UserRequestDTO;
import br.com.fiap.cp5.cp5_java.dto.UserResponseDTO;
import br.com.fiap.cp5.cp5_java.entity.UserEntity;
import br.com.fiap.cp5.cp5_java.entity.enuns.RoleEnum;
import br.com.fiap.cp5.cp5_java.exception.NotFoundException;
import br.com.fiap.cp5.cp5_java.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(UserResponseDTO::from)
                .toList();
    }

    public UserResponseDTO getById(Long id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(NotFoundException.forUser(id));

        return UserResponseDTO.from(user);
    }

    public void delete(Long id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(NotFoundException.forUser(id));

        repository.delete(user);
    }

    public UserResponseDTO save(@Valid UserRequestDTO filter) {
        UserEntity user = UserEntity.builder()
                .name(filter.getName())
                .email(filter.getEmail())
                .password(passwordEncoder.encode(filter.getPassword()))
                .role(RoleEnum.USER)
                .build();

        UserEntity saved = repository.save(user);

        return UserResponseDTO.from(saved);
    }

    public UserResponseDTO update(Long id, UserRequestDTO filter) {
        UserEntity user = repository.findById(id)
                .orElseThrow(NotFoundException.forUser(id));

        user.setName(filter.getName());
        user.setEmail(filter.getEmail());

        if (filter.getPassword() != null && !filter.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(filter.getPassword()));
        }

        user.setRole(RoleEnum.USER);

        return UserResponseDTO.from(repository.save(user));
    }

    public UserResponseDTO findByEmail(String email) {
        UserEntity user = repository.findByEmail(email)
                .orElseThrow(NotFoundException.forLogin());

        return UserResponseDTO.from(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = repository.findByEmail(username);

        if (user.isPresent()) {
            UserEntity usuario = user.get();
            return User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getPassword())
                    .roles(usuario.getRole().name())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

}

