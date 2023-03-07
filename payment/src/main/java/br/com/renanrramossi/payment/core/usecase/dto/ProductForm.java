package br.com.renanrramossi.payment.core.usecase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({"id", "estoque"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductForm implements Serializable {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("estoque")
  private Integer estoque;
}
