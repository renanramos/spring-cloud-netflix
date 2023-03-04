package br.com.renanrramossi.payment.interfaceadapter.mapper;

import br.com.renanrramossi.payment.core.domain.ProductSale;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductSaleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductSaleDTOMapper {

  ProductSaleDTOMapper INSTANCE = Mappers.getMapper(ProductSaleDTOMapper.class);

  @Mapping(source = "productId", target = "idProduto")
  @Mapping(source = "amount", target = "quantidade")
  ProductSaleDTO mapProductSaleDtoFrom(final ProductSale productSale);
}
