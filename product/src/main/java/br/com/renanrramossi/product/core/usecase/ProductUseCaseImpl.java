package br.com.renanrramossi.product.core.usecase;

import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.core.usecase.gateway.ProductGateway;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {

  private final ProductGateway productGateway;

  @Override
  public ProductDTO create(final ProductForm productForm) {
    return productGateway.create(productForm);
  }

  @Override
  public void delete(final Long id) {
    productGateway.delete(id);
  }

  @Override
  public Page<ProductDTO> findAll(final Pageable pageable) {
    return productGateway.findAll(pageable);
  }

  @Override
  public ProductDTO findById(final Long id) {
    return productGateway.findById(id);
  }

  @Override
  public ProductDTO update(final ProductForm productForm) {
    return productGateway.update(productForm);
  }
}
