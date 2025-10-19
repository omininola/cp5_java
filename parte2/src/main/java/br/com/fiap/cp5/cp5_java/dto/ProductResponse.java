package br.com.fiap.cp5.cp5_java.dto;


import br.com.fiap.cp5.cp5_java.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long idProduto;
    private String name;
    private String type;
    private String sector;
    private double size;
    private double unitPrice;

    public static ProductResponse from(ProductEntity produtosEntity) {
        return ProductResponse.builder()
                .idProduto(produtosEntity.getIdProduto())
                .name(produtosEntity.getName())
                .type(produtosEntity.getType())
                .sector(produtosEntity.getSector())
                .size(produtosEntity.getSize())
                .unitPrice(produtosEntity.getUnitPrice())
                .build();
    }

}