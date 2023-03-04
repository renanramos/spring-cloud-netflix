package br.com.renanrramossi.payment.interfaceadapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@JsonPropertyOrder({"id", "idProduto", "quantidade"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductSaleDTO extends RepresentationModel<ProductSaleDTO> implements Serializable {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("idProduto")
  private Long idProduto;

  @JsonProperty("quantidade")
  private Integer quantidade;

}
