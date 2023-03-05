package br.com.renanrramossi.payment.core.usecase;

import br.com.renanrramossi.payment.core.domain.Sale;
import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SaleUseCase {
  SaleDTO create(final Sale sale);

  Page<SaleDTO> findAll(final Pageable pageable);

  SaleDTO findById(final Long id);
}
