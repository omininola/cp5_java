package br.com.fiap.cp4.cp4_java.controller;

import br.com.fiap.cp4.cp4_java.dto.ProductResponse;
import br.com.fiap.cp4.cp4_java.entity.ProductEntity;
import br.com.fiap.cp4.cp4_java.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String listProducts(Model model) {
        var responses = productRepository.findAll()
                .stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());
        model.addAttribute("products", responses);
        return "list";
    }

    @GetMapping("/new")
    public String newProductsForm(Model model) {
        model.addAttribute("product", new ProductEntity());
        return "form";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") ProductEntity product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        model.addAttribute("product", product);

        return "form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("product") ProductEntity product) {

        product.setIdProduto(id);

        productRepository.save(product);

        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        productRepository.deleteById(id);

        return "redirect:/products";
    }
}

