package br.com.renanrramossi.payment.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "date")
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  private Date date;

  @Column(name = "total")
  private Double total;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = {CascadeType.REFRESH})
  private List<ProductSale> productSales;
}
