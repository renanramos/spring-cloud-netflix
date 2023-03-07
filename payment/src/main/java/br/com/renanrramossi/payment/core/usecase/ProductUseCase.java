package br.com.renanrramossi.payment.core.usecase;

import br.com.renanrramossi.payment.core.domain.Product;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductDTO;

public interface ProductUseCase {

  ProductDTO create(final Product product);

  ProductDTO findById(final Long id);
}
