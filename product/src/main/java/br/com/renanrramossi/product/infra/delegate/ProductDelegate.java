package br.com.renanrramossi.product.infra.delegate;

import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductDelegate {

  ProductDTO create(final ProductForm productForm);

  Page<ProductDTO> findAll(final Pageable pageable);

  void delete(final Long id);

  ProductDTO findById(final Long id);

  ProductDTO update(final ProductForm productForm);
}
