package br.com.renanrramossi.payment.interfaceadapter.gateway;

import br.com.renanrramossi.payment.core.domain.ProductSale;
import br.com.renanrramossi.payment.core.domain.Sale;
import br.com.renanrramossi.payment.core.usecase.exception.ResourceNotFoundException;
import br.com.renanrramossi.payment.core.usecase.gateway.SaleGateway;
import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import br.com.renanrramossi.payment.interfaceadapter.mapper.SaleDTOMapper;
import br.com.renanrramossi.payment.interfaceadapter.repository.ProductSaleRepository;
import br.com.renanrramossi.payment.interfaceadapter.repository.SaleRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class SaleGatewayImpl implements SaleGateway {

  private final SaleRepository saleRepository;

  private final ProductSaleRepository productSaleRepository;

  @Override
  public SaleDTO create(final Sale sale) {

    final Sale newSale = saleRepository.save(sale);

    final List<ProductSale> savedProducts = new ArrayList<>();

    newSale.getProductSales().forEach(productSale -> {
      productSale.setSale(newSale);
      savedProducts.add(productSaleRepository.save(productSale));
    });

    newSale.setProductSales(savedProducts);
    return SaleDTOMapper.INSTANCE.mapSaleDTOFrom(newSale);
  }

  @Override
  public Page<SaleDTO> findAll(final Pageable pageable) {

    final Page<Sale> salePage = saleRepository.findAll(pageable);

    return salePage.map(SaleDTOMapper.INSTANCE::mapSaleDTOFrom);
  }

  @Override
  public SaleDTO findById(final Long id) {

    final Sale sale = saleRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

    return SaleDTOMapper.INSTANCE.mapSaleDTOFrom(sale);
  }
}
