package br.com.fiap.cp4.cp4_java.repository;

import br.com.fiap.cp4.cp4_java.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
