package br.com.renanrramossi.payment.core.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

  @Id
  private Long id;

  @Column(name = "stock", length = 10)
  private Integer stock;
}
