package br.com.renanrramossi.product.interfaceadapter.mapper;

import br.com.renanrramossi.product.core.domain.ProductDomain;
import br.com.renanrramossi.product.interfaceadapter.dto.response.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDtoMapper {

  ProductDtoMapper INSTANCE = Mappers.getMapper(ProductDtoMapper.class);

  ProductDTO mapProductDtoFrom(final ProductDomain productDomain);

}
