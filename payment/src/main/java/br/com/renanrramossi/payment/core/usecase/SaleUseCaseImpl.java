package br.com.renanrramossi.payment.core.usecase;

import br.com.renanrramossi.payment.core.domain.Sale;
import br.com.renanrramossi.payment.core.usecase.gateway.SaleGateway;
import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class SaleUseCaseImpl implements SaleUseCase{

  private final SaleGateway saleGateway;

  @Override
  public SaleDTO create(final Sale sale) {
    return saleGateway.create(sale);
  }

  @Override
  public Page<SaleDTO> findAll(final Pageable pageable) {
    return saleGateway.findAll(pageable);
  }

  @Override
  public SaleDTO findById(final Long id) {
    return saleGateway.findById(id);
  }
}
