package br.com.renanrramossi.payment.interfaceadapter.common;

import br.com.renanrramossi.payment.core.domain.Product;
import br.com.renanrramossi.payment.core.domain.ProductSale;
import br.com.renanrramossi.payment.core.domain.Sale;
import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public final class CommonTestUtils {

  private static final long ID = 1L;
  private static final double TOTAL = 20.5;
  private static final long PRODUCT_ID = 2L;
  public static final int AMOUNT = 20;

  public static Sale getSale() {
    return Sale
        .builder()
        .date(Date.from(Instant.now()))
        .id(ID)
        .total(TOTAL)
        .productSales(getProducts())
        .build();
  }

  public static List<ProductSale> getProducts() {
    return Arrays.asList(getProductSale(), getProductSale());
  }

  public static ProductSale getProductSale() {
    return ProductSale.builder()
        .productId(PRODUCT_ID)
        .id(ID)
        .amount(AMOUNT)
        .build();
  }

  public static Product getProduct() {
    return Product
        .builder()
        .id(ID)
        .stock(AMOUNT)
        .build();
  }

}
