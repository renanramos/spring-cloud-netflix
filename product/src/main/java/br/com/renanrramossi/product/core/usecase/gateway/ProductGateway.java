package br.com.renanrramossi.product.core.usecase.gateway;

import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductGateway {

  ProductDTO create(final ProductForm productForm);

  Page<ProductDTO> findAll(final Pageable pageable);

  ProductDTO findById(final Long id);

  ProductDTO update(final ProductForm productForm);

  void delete(final Long id);
}
