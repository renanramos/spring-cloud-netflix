package br.com.renanrramossi.product.interfaceadapter.mapper;

import br.com.renanrramossi.product.core.domain.ProductDomain;
import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.response.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDomainMapper {

  ProductDomainMapper INSTANCE = Mappers.getMapper(ProductDomainMapper.class);

  ProductDomain mapProductDomainFrom(final ProductForm productForm);

  ProductDomain mapProductDomainFrom(final ProductDTO productDTO);
}
