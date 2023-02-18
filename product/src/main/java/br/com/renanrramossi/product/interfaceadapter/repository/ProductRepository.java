package br.com.renanrramossi.product.interfaceadapter.repository;

import br.com.renanrramossi.product.core.domain.ProductDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDomain, Long> {

}
