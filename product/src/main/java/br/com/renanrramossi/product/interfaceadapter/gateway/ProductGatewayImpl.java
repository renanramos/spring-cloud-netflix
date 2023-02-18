package br.com.renanrramossi.product.interfaceadapter.gateway;

import br.com.renanrramossi.product.core.domain.ProductDomain;
import br.com.renanrramossi.product.core.usecase.exception.ResourceNotFoundException;
import br.com.renanrramossi.product.core.usecase.gateway.ProductGateway;
import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.response.ProductDTO;
import br.com.renanrramossi.product.interfaceadapter.mapper.ProductDomainMapper;
import br.com.renanrramossi.product.interfaceadapter.mapper.ProductDtoMapper;
import br.com.renanrramossi.product.interfaceadapter.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

  private final ProductRepository productRepository;

  @Override
  public ProductDTO create(final ProductForm productForm) {

    final ProductDomain productDomain = ProductDomainMapper.INSTANCE.mapProductDomainFrom(productForm);

    return ProductDtoMapper.INSTANCE.mapProductDtoFrom(productRepository.save(productDomain));
  }

  @Override
  public Page<ProductDTO> findAll(final Pageable pageable) {
    var page = productRepository.findAll(pageable);

    return page.map(ProductDtoMapper.INSTANCE::mapProductDtoFrom);
  }

  @Override
  public ProductDTO findById(final Long id) {

    final ProductDomain productDomain = getProductDomainById(id);

    return ProductDtoMapper.INSTANCE.mapProductDtoFrom(productDomain);
  }

  @Override
  public ProductDTO update(ProductForm productForm) {
    final ProductDomain product = getProductDomainById(productForm.getId());

    final ProductDomain productDomain = productRepository.save(product);

    return ProductDtoMapper.INSTANCE.mapProductDtoFrom(productDomain);
  }

  @Override
  public void delete(final Long id) {
    final ProductDomain product = getProductDomainById(id);

    productRepository.delete(product);
  }

  private ProductDomain getProductDomainById(final Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
  }
}
