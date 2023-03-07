package br.com.renanrramossi.payment.infra.delegate;

import br.com.renanrramossi.payment.core.domain.Product;
import br.com.renanrramossi.payment.core.usecase.ProductUseCase;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductDelegateImpl implements ProductDelegate{

  private final ProductUseCase productUseCase;

  @Override
  public ProductDTO create(final Product product) {
    return productUseCase.create(product);
  }

  @Override
  public ProductDTO findById(Long id) {
    return productUseCase.findById(id);
  }
}
