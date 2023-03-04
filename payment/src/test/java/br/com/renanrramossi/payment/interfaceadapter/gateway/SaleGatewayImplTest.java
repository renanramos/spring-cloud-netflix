package br.com.renanrramossi.payment.interfaceadapter.gateway;

import static br.com.renanrramossi.payment.interfaceadapter.common.CommonTestUtils.getSale;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import br.com.renanrramossi.payment.core.domain.Sale;
import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import br.com.renanrramossi.payment.interfaceadapter.mapper.SaleDTOMapper;
import br.com.renanrramossi.payment.interfaceadapter.repository.SaleRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
class SaleGatewayImplTest {

  @Mock
  private SaleRepository mockSaleRepository;

  @Mock
  private Pageable mockPageable;

  @InjectMocks
  private SaleGatewayImpl saleGateway;

  @Test
  void create_withValidSale_shouldReturnSaleDTO() {
    final Sale sale = getSale();

    when(mockSaleRepository.save(any(Sale.class))).thenReturn(sale);

    final SaleDTO saleDTO = saleGateway.create(sale);

    assertThat(saleDTO).isNotNull();
    assertThat(saleDTO.getId()).isEqualTo(sale.getId());
    assertThat(saleDTO.getData()).isEqualTo(sale.getDate());
    assertThat(saleDTO.getTotal()).isEqualTo(sale.getTotal());
    assertThat(saleDTO.getProdutos())
        .isNotEmpty()
        .hasSize(sale.getProductSales().size());
  }

  @Test
  void findAll_withPageable_shouldReturnPage() {

    final Page<Sale> salePage = getSalePage();

    when(mockSaleRepository.findAll(mockPageable)).thenReturn(salePage);

    final Page<SaleDTO> response = saleGateway.findAll(mockPageable);

    assertThat(response).isNotNull();
    assertThat(response.getTotalElements()).isEqualTo(salePage.getTotalElements());
    assertThat(response.getTotalPages()).isEqualTo(salePage.getTotalPages());

    final List<SaleDTO> responseContent = response.getContent();

    final List<SaleDTO> pageSaleContent = mapSaleTOSaleDTO(salePage);

    assertThat(responseContent)
        .isNotEmpty()
        .hasSize(pageSaleContent.size())
        .containsExactlyInAnyOrderElementsOf(pageSaleContent);
  }

  private List<SaleDTO> mapSaleTOSaleDTO(Page<Sale> salePage) {
    return salePage.getContent()
            .stream()
            .map(SaleDTOMapper.INSTANCE::mapSaleDTOFrom)
            .collect(Collectors.toList());
  }

  private PageImpl<Sale> getSalePage() {
    final Pageable pageable = PageRequest.of(1, 10, Sort.unsorted());
    return new PageImpl<>(Collections.nCopies(3, getSale()), pageable, 3);
  }
}
