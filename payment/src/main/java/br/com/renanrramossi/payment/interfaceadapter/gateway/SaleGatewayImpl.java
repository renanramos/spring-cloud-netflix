package br.com.renanrramossi.payment.interfaceadapter.gateway;

import br.com.renanrramossi.payment.core.domain.Sale;
import br.com.renanrramossi.payment.core.usecase.gateway.SaleGateway;
import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import br.com.renanrramossi.payment.interfaceadapter.mapper.SaleDTOMapper;
import br.com.renanrramossi.payment.interfaceadapter.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class SaleGatewayImpl implements SaleGateway {

  private final SaleRepository saleRepository;

  @Override
  public SaleDTO create(final Sale sale) {

    final Sale newSale = saleRepository.save(sale);

    return SaleDTOMapper.INSTANCE.mapSaleDTOFrom(newSale);
  }

  @Override
  public Page<SaleDTO> findAll(final Pageable pageable) {

    final Page<Sale> salePage = saleRepository.findAll(pageable);

    return salePage.map(SaleDTOMapper.INSTANCE::mapSaleDTOFrom);
  }
}
