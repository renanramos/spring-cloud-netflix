package br.com.renanrramossi.payment.infra.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public abstract class BaseController {

  private static final String ID = "id";
  private static final String DESC = "desc";

  protected Direction getSortDirection(final String direction) {
    return DESC.equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
  }

  protected Pageable getPageable(final int page, final int limit, final String direction) {
    return PageRequest.of(page, limit, Sort.by(getSortDirection(direction), ID));
  }

  private void orderList(final List<SaleDTO> items) {
    items
        .forEach(item ->
            item.add(linkTo(methodOn(SaleController.class)
                .findById(item.getId())).withSelfRel()));
  }

  protected PagedModel<EntityModel<SaleDTO>> buildPagedModel(
      final PagedResourcesAssembler<SaleDTO> assembler,
      final Page<SaleDTO> saleDTOS) {

    orderList(saleDTOS.toList());

    return assembler.toModel(saleDTOS);
  }

  protected void setSelfLink(final SaleDTO saleDTO) {
    saleDTO.add(linkTo(methodOn(SaleController.class)
        .findById(saleDTO.getId())).withSelfRel());
  }
}
