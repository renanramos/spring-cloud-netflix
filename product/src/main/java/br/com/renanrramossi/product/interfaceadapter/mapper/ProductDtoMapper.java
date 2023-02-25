package br.com.renanrramossi.product.interfaceadapter.mapper;

import br.com.renanrramossi.product.core.domain.Product;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDtoMapper {

  ProductDtoMapper INSTANCE = Mappers.getMapper(ProductDtoMapper.class);


  @Mapping(source = "name", target = "nome")
  @Mapping(source = "stock", target = "estoque")
  @Mapping(source = "price", target = "preco")
  ProductDTO mapProductDtoFrom(final Product product);

}
