package br.com.renanrramossi.product.interfaceadapter.mapper;

import static br.com.renanrramossi.product.interfaceadapter.common.AssertCommons.assertProduct;
import static br.com.renanrramossi.product.interfaceadapter.common.TestUtils.getProduct;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.renanrramossi.product.core.domain.Product;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import org.junit.jupiter.api.Test;

class ProductDtoMapperTest {

  @Test
  void mapProductDtoFromProduct_withProduct_shouldReturnProductDTO() {
    final Product product = getProduct();

    final ProductDTO productDTO = ProductDtoMapper.INSTANCE.mapProductDtoFromProduct(product);

    assertProduct(product, productDTO);
  }

  @Test
  void mapProductDtoFromProduct_withNullProduct_shouldReturnNull() {
    assertThat(ProductDtoMapper.INSTANCE.mapProductDtoFromProduct(null)).isNull();
  }

}
