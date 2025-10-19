package br.com.fiap.cp4.cp4_java.controller;

import br.com.fiap.cp4.cp4_java.dto.UserRequestDTO;
import br.com.fiap.cp4.cp4_java.dto.UserResponseDTO;
import br.com.fiap.cp4.cp4_java.entity.enuns.RoleEnum;
import br.com.fiap.cp4.cp4_java.service.UserService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public String listUsers(Model model, Principal principal) {
        String email = principal.getName();

        UserResponseDTO usuarioLogado = service.findByEmail(email);

        if (usuarioLogado.getRole().equals(RoleEnum.ADMIN)) {
            model.addAttribute("users", service.getAll());
        } else {
            model.addAttribute("users", List.of(usuarioLogado));
        }

        return "list-users";
    }


    @GetMapping("/new")
    public String newUsersForm(Model model) {
        model.addAttribute("usuario", new UserRequestDTO());
        return "form-user";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("usuario") UserRequestDTO filter) {
        service.save(filter);
        return "redirect:/home";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        UserResponseDTO response = service.getById(id);

        UserRequestDTO dto = new UserRequestDTO();
        dto.setName(response.getName());
        dto.setEmail(response.getEmail());

        model.addAttribute("usuario", dto);
        model.addAttribute("id", id);

        return "form-user";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("usuario") UserRequestDTO filter) {
        service.update(id, filter);
        return "redirect:/home";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/users";
    }

}
