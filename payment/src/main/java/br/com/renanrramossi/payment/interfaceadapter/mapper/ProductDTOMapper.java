package br.com.renanrramossi.payment.interfaceadapter.mapper;

import br.com.renanrramossi.payment.core.domain.Product;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDTOMapper {

  ProductDTOMapper INSTANCE = Mappers.getMapper(ProductDTOMapper.class);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "stock", target = "estoque")
  ProductDTO mapProductDtoFrom(final Product product);
}
