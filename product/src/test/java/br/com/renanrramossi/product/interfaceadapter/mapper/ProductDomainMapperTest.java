package br.com.renanrramossi.product.interfaceadapter.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import br.com.renanrramossi.product.core.domain.Product;
import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ProductDomainMapperTest {

  @Test
  void mapProductDomainFrom_withProductForm_shouldReturnProductDomain() {
    final ProductForm productForm = ProductForm.builder()
        .id(1L)
        .estoque(10)
        .nome("Product 1")
        .preco(BigDecimal.ONE.doubleValue())
        .build();

    final Product product = ProductDomainMapper.INSTANCE.mapProductDomainFrom(productForm);

    assertThat(product).isNotNull();
    assertThat(product.getId()).isEqualTo(productForm.getId());
    assertThat(product.getStock()).isEqualTo(productForm.getEstoque());
    assertThat(product.getName()).isEqualTo(productForm.getNome());
    assertThat(product.getPrice()).isEqualTo(productForm.getPreco().doubleValue());
  }

  @Test
  void mapProductDomainFrom_withProductDTO_shouldReturnProductDomain() {
    final ProductDTO productDTO = ProductDTO.builder()
        .id(1L)
        .estoque(10)
        .nome("Product 1")
        .preco(BigDecimal.ONE.doubleValue())
        .build();

    final Product product = ProductDomainMapper.INSTANCE.mapProductDomainFrom(productDTO);

    assertThat(product).isNotNull();
    assertThat(product.getId()).isEqualTo(productDTO.getId());
    assertThat(product.getStock()).isEqualTo(productDTO.getEstoque());
    assertThat(product.getName()).isEqualTo(productDTO.getNome());
    assertThat(product.getPrice()).isEqualTo(productDTO.getPreco().doubleValue());
  }
}
