package br.com.renanrramossi.product.interfaceadapter.gateway;

import br.com.renanrramossi.product.core.domain.Product;
import br.com.renanrramossi.product.core.usecase.exception.ResourceNotFoundException;
import br.com.renanrramossi.product.core.usecase.gateway.ProductGateway;
import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
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

    final Product product = ProductDomainMapper.INSTANCE.mapProductDomainFromProductForm(productForm);

    return ProductDtoMapper.INSTANCE.mapProductDtoFromProduct(productRepository.save(product));
  }

  @Override
  public Page<ProductDTO> findAll(final Pageable pageable) {
    var page = productRepository.findAll(pageable);

    return page.map(ProductDtoMapper.INSTANCE::mapProductDtoFromProduct);
  }

  @Override
  public ProductDTO findById(final Long id) {

    final Product product = getProductDomainById(id);

    return ProductDtoMapper.INSTANCE.mapProductDtoFromProduct(product);
  }

  @Override
  public ProductDTO update(ProductForm productForm) {
    final Product product = getProductDomainById(productForm.getId());

    final Product productDomain = productRepository.save(product);

    return ProductDtoMapper.INSTANCE.mapProductDtoFromProduct(productDomain);
  }

  @Override
  public void delete(final Long id) {
    final Product product = getProductDomainById(id);

    productRepository.delete(product);
  }

  private Product getProductDomainById(final Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
  }
}
