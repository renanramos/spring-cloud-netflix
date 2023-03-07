package br.com.renanrramossi.payment.core.usecase.dto;

import br.com.renanrramossi.payment.core.domain.ProductSale;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({
    "id",
    "date",
    "total",
    "productSales"
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleForm implements Serializable {

  @JsonPropertyOrder("id")
  private Long id;

  @JsonPropertyOrder("date")
  private Date date;

  @JsonPropertyOrder("total")
  private Double total;

  @JsonPropertyOrder("productSales")
  private List<ProductSale> productSales;
}
