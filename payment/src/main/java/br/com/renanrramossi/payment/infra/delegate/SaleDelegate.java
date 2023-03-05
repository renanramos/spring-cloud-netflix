package br.com.renanrramossi.payment.infra.delegate;

import br.com.renanrramossi.payment.core.domain.Sale;
import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SaleDelegate {
  SaleDTO create(final Sale sale);

  Page<SaleDTO> findAll(final Pageable pageable);

  SaleDTO findById(final Long id);
}
