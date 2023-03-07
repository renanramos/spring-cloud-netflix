package br.com.renanrramossi.payment.interfaceadapter.repository;

import br.com.renanrramossi.payment.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
