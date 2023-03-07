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

public abstract class BaseController<T> {

  private static final String DATA = "date";
  private static final String DESC = "desc";

  protected Direction getSortDirection(final String direction) {
    return DESC.equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
  }

  protected Pageable getPageable(final int page, final int limit, final String direction) {
    return PageRequest.of(page, limit, Sort.by(getSortDirection(direction), DATA));
  }

  protected PagedModel<EntityModel<T>> buildPagedModel(
      final PagedResourcesAssembler<T> assembler,
      final Page<T> items) {

    orderList(items.toList());

    return assembler.toModel(items);
  }

  private void orderList(final List<T> items) {
    items.forEach(this::setSelfLink);
  }

  protected abstract void setSelfLink(final T t);
}
