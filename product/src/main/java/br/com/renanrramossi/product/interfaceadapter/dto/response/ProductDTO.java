package br.com.renanrramossi.product.interfaceadapter.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({
    "id",
    "nome",
    "estoque",
    "preco"
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {

  @JsonPropertyOrder("id")
  private Long id;

  @JsonPropertyOrder("nome")
  private String nome;

  @JsonPropertyOrder("estoque")
  private Integer estoque;

  @JsonPropertyOrder("preco")
  private Double preco;
}
