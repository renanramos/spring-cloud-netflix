package br.com.renanrramossi.payment.interfaceadapter.repository;

import br.com.renanrramossi.payment.core.domain.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {

}
