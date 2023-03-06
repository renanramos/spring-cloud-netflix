package br.com.renanrramossi.payment.infra.delegate;

import br.com.renanrramossi.payment.core.domain.Product;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductDTO;

public interface ProductDelegate {
  ProductDTO create(final Product product);

  ProductDTO findById(final Long id);
}
