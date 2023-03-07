package br.com.renanrramossi.payment.core.usecase.gateway;

import br.com.renanrramossi.payment.core.domain.Product;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductDTO;

public interface ProductGateway {

  ProductDTO create(final Product product);

  ProductDTO findById(final Long id);
}
