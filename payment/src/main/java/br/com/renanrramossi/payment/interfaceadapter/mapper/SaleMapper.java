package br.com.renanrramossi.payment.interfaceadapter.mapper;

import br.com.renanrramossi.payment.core.domain.Sale;
import br.com.renanrramossi.payment.core.usecase.dto.SaleForm;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SaleMapper {

  SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

  @Named("mapSaleFrom")
  Sale mapSaleFrom(final SaleForm saleForm);
}
