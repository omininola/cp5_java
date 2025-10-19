package br.com.fiap.cp5.cp5_java.repository;

import br.com.fiap.cp5.cp5_java.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
