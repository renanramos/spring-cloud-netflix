package br.com.renanrramossi.payment.interfaceadapter.gateway;

import br.com.renanrramossi.payment.core.domain.Product;
import br.com.renanrramossi.payment.core.usecase.exception.ResourceNotFoundException;
import br.com.renanrramossi.payment.core.usecase.gateway.ProductGateway;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductDTO;
import br.com.renanrramossi.payment.interfaceadapter.mapper.ProductDTOMapper;
import br.com.renanrramossi.payment.interfaceadapter.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

  private final ProductRepository productRepository;

  @Override
  public ProductDTO create(final Product product) {

    final Product newProduct = productRepository.save(product);

    return ProductDTOMapper.INSTANCE.mapProductDtoFrom(newProduct);
  }

  @Override
  public ProductDTO findById(final Long id) {

    final Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No products found with ID"));

    return ProductDTOMapper.INSTANCE.mapProductDtoFrom(product);
  }
}
