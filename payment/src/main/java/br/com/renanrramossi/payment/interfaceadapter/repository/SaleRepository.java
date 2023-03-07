package br.com.renanrramossi.payment.interfaceadapter.repository;

import br.com.renanrramossi.payment.core.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
