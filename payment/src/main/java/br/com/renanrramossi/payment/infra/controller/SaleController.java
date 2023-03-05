package br.com.renanrramossi.payment.infra.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.renanrramossi.payment.core.usecase.dto.SaleForm;
import br.com.renanrramossi.payment.infra.delegate.SaleDelegate;
import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import br.com.renanrramossi.payment.interfaceadapter.mapper.SaleMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController extends BaseController {

  private final SaleDelegate saleDelegate;

  private final PagedResourcesAssembler<SaleDTO> assembler;

  @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
  public SaleDTO findById(@PathVariable("id") final Long id) {
    final SaleDTO saleDTO = saleDelegate.findById(id);
    saleDTO.add(linkTo(methodOn(SaleController.class).findById(id)).withSelfRel());
    return saleDTO;
  }

  @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
  public ResponseEntity<PagedModel<EntityModel<SaleDTO>>> findAll(
      @RequestParam(value = "page", defaultValue = "0") final int page,
      @RequestParam(value = "limit", defaultValue = "10") final int limit,
      @RequestParam(value = "direction", defaultValue = "asc")final String direction) {

    final Pageable pageable = getPageable(page, limit, direction);

    final Page<SaleDTO> saleDTOPage = saleDelegate.findAll(pageable);

    return new ResponseEntity<>(buildPagedModel(assembler, saleDTOPage), HttpStatus.OK);
  }

  @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
      consumes = {"application/json", "application/xml", "application/x-yaml"})
  public ResponseEntity<SaleDTO> create(@NonNull @RequestBody final SaleForm saleForm) {

    final SaleDTO saleDTO = saleDelegate.create(SaleMapper.INSTANCE.mapSaleFrom(saleForm));

    setSelfLink(saleDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(saleDTO);
  }

}
