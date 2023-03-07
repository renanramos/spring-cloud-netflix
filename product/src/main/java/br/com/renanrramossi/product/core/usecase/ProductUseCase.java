package br.com.renanrramossi.product.core.usecase;

import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductUseCase {
  ProductDTO create(final ProductForm productForm);

  void delete(final Long id);

  Page<ProductDTO> findAll(final Pageable pageable);

  ProductDTO findById(final Long id);

  ProductDTO update(final ProductForm productForm);

}
