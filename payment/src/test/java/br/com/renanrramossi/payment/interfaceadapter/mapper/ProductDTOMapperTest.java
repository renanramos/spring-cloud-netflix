package br.com.renanrramossi.payment.interfaceadapter.mapper;

import static br.com.renanrramossi.payment.interfaceadapter.common.CommonTestUtils.getProduct;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.renanrramossi.payment.core.domain.Product;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductDTO;
import org.junit.jupiter.api.Test;

class ProductDTOMapperTest {

  @Test
  void mapProductDtoFrom_withProduct_shouldReturnProductDto() {

    final Product product = getProduct();

    final ProductDTO productDTO = ProductDTOMapper.INSTANCE.mapProductDtoFrom(product);

    assertThat(productDTO).isNotNull();
    assertThat(productDTO.getId()).isEqualTo(product.getId());
    assertThat(productDTO.getEstoque()).isEqualTo(product.getStock());
  }

  @Test
  void mapProductDtoFrom_withNullProduct_shouldReturnNull() {
    assertThat(ProductDTOMapper.INSTANCE.mapProductDtoFrom(null)).isNull();
  }
}
