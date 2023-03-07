package br.com.renanrramossi.payment.interfaceadapter.mapper;

import br.com.renanrramossi.payment.core.domain.Product;
import br.com.renanrramossi.payment.core.usecase.dto.ProductForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  @Mapping(source = "estoque", target = "stock")
  Product mapProductFrom(final ProductForm productForm);
}
