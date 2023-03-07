package br.com.renanrramossi.payment.core.usecase;

import br.com.renanrramossi.payment.core.domain.Product;
import br.com.renanrramossi.payment.core.usecase.gateway.ProductGateway;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase{

  private final ProductGateway productGateway;

  @Override
  public ProductDTO create(final Product product) {
    return productGateway.create(product);
  }

  @Override
  public ProductDTO findById(final Long id) {
    return productGateway.findById(id);
  }
}
