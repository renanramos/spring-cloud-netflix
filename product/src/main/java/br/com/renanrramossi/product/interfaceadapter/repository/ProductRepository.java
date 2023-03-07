package br.com.renanrramossi.product.interfaceadapter.repository;

import br.com.renanrramossi.product.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
