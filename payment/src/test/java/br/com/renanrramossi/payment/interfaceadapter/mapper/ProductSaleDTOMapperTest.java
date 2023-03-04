package br.com.renanrramossi.payment.interfaceadapter.mapper;

import static br.com.renanrramossi.payment.interfaceadapter.common.CommonTestUtils.getProductSale;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import br.com.renanrramossi.payment.core.domain.ProductSale;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductSaleDTO;
import org.junit.jupiter.api.Test;

class ProductSaleDTOMapperTest {

  @Test
  void mapProductSaleDtoFrom_withProductSale_shouldReturnProductSaleDTO() {

    final ProductSale productSale = getProductSale();

    final ProductSaleDTO productSaleDTO = ProductSaleDTOMapper.INSTANCE.mapProductSaleDtoFrom(productSale);

    assertThat(productSaleDTO).isNotNull();
    assertThat(productSaleDTO.getId()).isEqualTo(productSale.getId());
    assertThat(productSaleDTO.getIdProduto()).isEqualTo(productSale.getProductId());
    assertThat(productSaleDTO.getQuantidade()).isEqualTo(productSale.getAmount());
  }

  @Test
  void mapProductSaleDtoFrom_withNullProductSale_shouldReturnProductSaleDTO() {
    assertThat(ProductSaleDTOMapper.INSTANCE.mapProductSaleDtoFrom(null)).isNull();
  }
}
