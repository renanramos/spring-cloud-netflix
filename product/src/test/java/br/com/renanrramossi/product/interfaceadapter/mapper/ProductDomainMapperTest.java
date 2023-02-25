package br.com.renanrramossi.product.interfaceadapter.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import br.com.renanrramossi.product.core.domain.Product;
import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ProductDomainMapperTest {

  public static final long ID = 1L;
  public static final int ESTOQUE = 10;
  public static final String PRODUCT_1 = "Product 1";
  public static final double PRECO = BigDecimal.ONE.doubleValue();

  @Test
  void mapProductDomainFrom_withProductForm_shouldReturnProductDomain() {
    final ProductForm productForm = getProductForm();

    final Product product = ProductDomainMapper.INSTANCE.mapProductDomainFromProductForm(productForm);

    assertThat(product).isNotNull();
    assertThat(product.getId()).isEqualTo(productForm.getId());
    assertThat(product.getStock()).isEqualTo(productForm.getEstoque());
    assertThat(product.getName()).isEqualTo(productForm.getNome());
    assertThat(product.getPrice()).isEqualTo(productForm.getPreco().doubleValue());
  }

  @Test
  void mapProductDomainFrom_withNullProductForm_shouldReturnNull() {
    assertThat(ProductDomainMapper.INSTANCE.mapProductDomainFromProductForm(null)).isNull();
  }

  @Test
  void mapProductDomainFrom_withProductDTO_shouldReturnProductDomain() {
    final ProductDTO productDTO = getProductDTO();

    final Product product = ProductDomainMapper.INSTANCE.mapProductDomainFromProductDTO(productDTO);

    assertThat(product).isNotNull();
    assertThat(product.getId()).isEqualTo(productDTO.getId());
    assertThat(product.getStock()).isEqualTo(productDTO.getEstoque());
    assertThat(product.getName()).isEqualTo(productDTO.getNome());
    assertThat(product.getPrice()).isEqualTo(productDTO.getPreco().doubleValue());
  }

  @Test
  void mapProductDomainFrom_withNullProductDTO_shouldReturnNull() {
    assertThat(ProductDomainMapper.INSTANCE.mapProductDomainFromProductDTO(null)).isNull();
  }

  private ProductDTO getProductDTO() {
    return ProductDTO.builder()
        .id(ID)
        .estoque(ESTOQUE)
        .nome(PRODUCT_1)
        .preco(PRECO)
        .build();
  }

  private ProductForm getProductForm() {
    return ProductForm.builder()
        .id(ID)
        .estoque(ESTOQUE)
        .nome(PRODUCT_1)
        .preco(PRECO)
        .build();
  }
}
