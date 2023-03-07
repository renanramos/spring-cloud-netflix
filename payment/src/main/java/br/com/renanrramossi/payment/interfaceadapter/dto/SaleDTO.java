package br.com.renanrramossi.payment.interfaceadapter.dto;

import br.com.renanrramossi.payment.core.domain.ProductSale;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@JsonPropertyOrder({
    "id",
    "data",
    "total",
    "produtos"
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SaleDTO extends RepresentationModel<SaleDTO> implements Serializable {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("data")
  private Date data;

  @JsonProperty("total")
  private Double total;

  @JsonProperty("produtos")
  private List<ProductSaleDTO> produtos;
}
