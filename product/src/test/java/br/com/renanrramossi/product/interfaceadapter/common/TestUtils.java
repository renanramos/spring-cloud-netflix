package br.com.renanrramossi.product.interfaceadapter.common;

import br.com.renanrramossi.product.core.domain.Product;
import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import java.math.BigDecimal;

public final class TestUtils {

  public static final long ID = 1L;
  public static final int ESTOQUE = 10;
  public static final String PRODUCT_1 = "Product 1";
  public static final double PRECO = BigDecimal.ONE.doubleValue();

  public static ProductDTO getProductDTO() {
    return ProductDTO.builder()
        .id(ID)
        .estoque(ESTOQUE)
        .nome(PRODUCT_1)
        .preco(PRECO)
        .build();
  }

  public static ProductForm getProductForm() {
    return ProductForm.builder()
        .id(ID)
        .estoque(ESTOQUE)
        .nome(PRODUCT_1)
        .preco(PRECO)
        .build();
  }

  public static Product getProduct() {
    return Product.builder()
        .id(ID)
        .name(PRODUCT_1)
        .stock(ESTOQUE)
        .price(PRECO)
        .build();
  }
}
