package br.com.fiap.cp4.cp4_java.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_PRODUTOS_CP5")

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProductEntity {

    @Id
    @Column(name = "ID_PRODUTO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto")
    @SequenceGenerator(name = "produto", sequenceName = "SQ_T_PRODUTOS_CP5", allocationSize = 1)
    private Long idProduto;

    @Column(name = "NAME_PRODUCT", length = 100, nullable = false)
    private String name;

    @Column(name = "TYPE_PRODUCT", length = 100, nullable = false)
    private String type;

    @Column(name = "SECTOR", length = 200, nullable = false)
    private String sector;

    @Column(name = "SIZE_PRODUCT", nullable = false)
    private double size;

    @Column(name = "UNIT_PRICE", nullable = false)
    private double unitPrice;


}
