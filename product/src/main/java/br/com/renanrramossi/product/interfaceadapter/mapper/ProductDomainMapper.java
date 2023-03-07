package br.com.renanrramossi.product.interfaceadapter.mapper;

import br.com.renanrramossi.product.core.domain.Product;
import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDomainMapper {

  ProductDomainMapper INSTANCE = Mappers.getMapper(ProductDomainMapper.class);

  @Mapping(source = "nome", target = "name")
  @Mapping(source = "estoque", target = "stock")
  @Mapping(source = "preco", target = "price")
  Product mapProductDomainFromProductForm(final ProductForm productForm);

  @Mapping(source = "nome", target = "name")
  @Mapping(source = "estoque", target = "stock")
  @Mapping(source = "preco", target = "price")
  Product mapProductDomainFromProductDTO(final ProductDTO productDTO);
}
