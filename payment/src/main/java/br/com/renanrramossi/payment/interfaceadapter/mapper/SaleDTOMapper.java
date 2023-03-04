package br.com.renanrramossi.payment.interfaceadapter.mapper;

import br.com.renanrramossi.payment.core.domain.Sale;
import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SaleDTOMapper {

  SaleDTOMapper INSTANCE = Mappers.getMapper(SaleDTOMapper.class);

  SaleDTO mapSaleDTOFrom(final Sale sale);
}
