package br.com.renanrramossi.payment.core.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class ProductSale implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "productId", length = 10)
  private Long productId;

  @Column(name = "amount", length = 10)
  private Integer amount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private Sale sale;

}
