package br.com.renanrramossi.payment.infra.delegate;

import br.com.renanrramossi.payment.core.domain.Sale;
import br.com.renanrramossi.payment.core.usecase.SaleUseCase;
import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class SaleDelegateImpl implements SaleDelegate{

  private final SaleUseCase saleUseCase;

  @Override
  public SaleDTO create(final Sale sale) {
    return saleUseCase.create(sale);
  }

  @Override
  public Page<SaleDTO> findAll(final Pageable pageable) {
    return saleUseCase.findAll(pageable);
  }

  @Override
  public SaleDTO findById(final Long id) {
    return saleUseCase.findById(id);
  }
}
