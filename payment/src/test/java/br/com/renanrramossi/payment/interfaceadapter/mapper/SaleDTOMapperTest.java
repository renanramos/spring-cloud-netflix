package br.com.renanrramossi.payment.interfaceadapter.mapper;

import static br.com.renanrramossi.payment.interfaceadapter.common.CommonTestUtils.getSale;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.renanrramossi.payment.core.domain.Sale;
import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import org.junit.jupiter.api.Test;

class SaleDTOMapperTest {

  @Test
  void mapSaleDTOFrom_withSale_shouldReturnSaleDTO() {

    final Sale sale = getSale();

    final SaleDTO saleDTO = SaleDTOMapper.INSTANCE.mapSaleDTOFrom(sale);

    assertThat(saleDTO).isNotNull();
    assertThat(saleDTO.getId()).isEqualTo(sale.getId());
    assertThat(saleDTO.getData()).isEqualTo(sale.getDate());
    assertThat(saleDTO.getTotal()).isEqualTo(sale.getTotal());
  }

  @Test
  void mapSaleDTOFrom_withNullSale_shouldReturnNull() {
    assertThat(SaleDTOMapper.INSTANCE.mapSaleDTOFrom(null)).isNull();
  }

}
