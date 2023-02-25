package br.com.renanrramossi.product.interfaceadapter.mapper;

import static br.com.renanrramossi.product.interfaceadapter.common.AssertCommons.assertProduct;
import static br.com.renanrramossi.product.interfaceadapter.common.TestUtils.getProductDTO;
import static br.com.renanrramossi.product.interfaceadapter.common.TestUtils.getProductForm;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.renanrramossi.product.core.domain.Product;
import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import org.junit.jupiter.api.Test;

class ProductDomainMapperTest {

  @Test
  void mapProductDomainFrom_withProductForm_shouldReturnProductDomain() {
    final ProductForm productForm = getProductForm();

    final Product product = ProductDomainMapper.INSTANCE.mapProductDomainFromProductForm(productForm);

    assertProduct(productForm, product);
  }

  @Test
  void mapProductDomainFrom_withNullProductForm_shouldReturnNull() {
    assertThat(ProductDomainMapper.INSTANCE.mapProductDomainFromProductForm(null)).isNull();
  }

  @Test
  void mapProductDomainFrom_withProductDTO_shouldReturnProductDomain() {
    final ProductDTO productDTO = getProductDTO();

    final Product product = ProductDomainMapper.INSTANCE.mapProductDomainFromProductDTO(productDTO);

    assertProduct(productDTO, product);
  }
  @Test
  void mapProductDomainFrom_withNullProductDTO_shouldReturnNull() {
    assertThat(ProductDomainMapper.INSTANCE.mapProductDomainFromProductDTO(null)).isNull();
  }

}
