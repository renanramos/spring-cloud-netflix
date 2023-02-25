package br.com.renanrramossi.product.interfaceadapter.common;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.renanrramossi.product.core.domain.Product;
import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;

public final class AssertCommons {

  public static void assertProduct(ProductDTO productDTO, Product product) {
    assertThat(product).isNotNull();
    assertThat(product.getId()).isEqualTo(productDTO.getId());
    assertThat(product.getStock()).isEqualTo(productDTO.getEstoque());
    assertThat(product.getName()).isEqualTo(productDTO.getNome());
    assertThat(product.getPrice()).isEqualTo(productDTO.getPreco().doubleValue());
  }


  public static void assertProduct(final ProductForm productForm, final Product product) {
    assertThat(product).isNotNull();
    assertThat(product.getId()).isEqualTo(productForm.getId());
    assertThat(product.getStock()).isEqualTo(productForm.getEstoque());
    assertThat(product.getName()).isEqualTo(productForm.getNome());
    assertThat(product.getPrice()).isEqualTo(productForm.getPreco().doubleValue());
  }


  public static void assertProduct(final Product product, final ProductDTO productDTO) {
    assertThat(productDTO).isNotNull();
    assertThat(productDTO.getId()).isEqualTo(product.getId());
    assertThat(productDTO.getNome()).isEqualTo(product.getName());
    assertThat(productDTO.getEstoque()).isEqualTo(product.getStock());
    assertThat(productDTO.getPreco()).isEqualTo(product.getPrice());
  }
}
