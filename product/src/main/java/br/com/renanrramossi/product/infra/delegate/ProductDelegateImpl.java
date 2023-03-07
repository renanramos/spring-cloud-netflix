package br.com.renanrramossi.product.infra.delegate;

import br.com.renanrramossi.product.core.usecase.ProductUseCase;
import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ProductDelegateImpl implements ProductDelegate {

  @NonNull
  private final ProductUseCase productUseCase;

  @Override
  public ProductDTO create(final ProductForm productForm) {
    return productUseCase.create(productForm);
  }

  @Override
  public void delete(final Long productId) {
    productUseCase.delete(productId);
  }

  @Override
  public Page<ProductDTO> findAll(final Pageable pageable) {
    return productUseCase.findAll(pageable);
  }

  @Override
  public ProductDTO findById(final Long productId) {
    return productUseCase.findById(productId);
  }

  @Override
  public ProductDTO update(final ProductForm productForm) {
    return productUseCase.update(productForm);
  }
}
