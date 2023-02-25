package br.com.renanrramossi.product.interfaceadapter.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import br.com.renanrramossi.product.core.domain.Product;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import org.junit.jupiter.api.Test;

class ProductDtoMapperTest {

  @Test
  void mapProductDtoFromProduct_withProduct_shouldReturnProductDTO() {
    final Product product = getProduct();

    final ProductDTO productDTO = ProductDtoMapper.INSTANCE.mapProductDtoFromProduct(product);

    assertThat(productDTO).isNotNull();
    assertThat(productDTO.getId()).isEqualTo(product.getId());
    assertThat(productDTO.getNome()).isEqualTo(product.getName());
    assertThat(productDTO.getEstoque()).isEqualTo(product.getStock());
    assertThat(productDTO.getPreco()).isEqualTo(product.getPrice());
  }

  @Test
  void mapProductDtoFromProduct_withNullProduct_shouldReturnNull() {
    assertThat(ProductDtoMapper.INSTANCE.mapProductDtoFromProduct(null)).isNull();
  }

  private Product getProduct() {
    return Product.builder()
        .id(1L)
        .name("Product 1")
        .stock(10)
        .price(25.50)
        .build();
  }
}
