package br.com.renanrramossi.payment.interfaceadapter.mapper;

import br.com.renanrramossi.payment.core.domain.ProductSale;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductSaleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductSaleMapper {

  ProductSaleMapper INSTANCE = Mappers.getMapper(ProductSaleMapper.class);

  @Mapping(source = "idProduto", target = "productId")
  @Mapping(source = "quantidade", target = "amount")
  ProductSale mapProductSaleFrom(final ProductSaleDTO productSaleDTO);

}
