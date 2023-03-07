package br.com.renanrramossi.product.interfaceadapter.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@JsonPropertyOrder({
    "id",
    "nome",
    "estoque",
    "preco"
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO extends RepresentationModel<ProductDTO> implements Serializable {

  @JsonPropertyOrder("id")
  private Long id;

  @JsonPropertyOrder("nome")
  private String nome;

  @JsonPropertyOrder("estoque")
  private Integer estoque;

  @JsonPropertyOrder("preco")
  private Double preco;
}
