package br.com.renanrramossi.payment.interfaceadapter.mapper;

import br.com.renanrramossi.payment.core.domain.Sale;
import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProductSaleDTOMapper.class})
public interface SaleDTOMapper {

  SaleDTOMapper INSTANCE = Mappers.getMapper(SaleDTOMapper.class);

  @Mapping(source = "date", target = "data")
  @Mapping(source = "productSales", target = "produtos", qualifiedByName = "mapProductSaleDtoFrom")
  SaleDTO mapSaleDTOFrom(final Sale sale);
}
