package br.com.renanrramossi.payment.infra.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.renanrramossi.payment.core.usecase.dto.ProductForm;
import br.com.renanrramossi.payment.core.usecase.dto.SaleForm;
import br.com.renanrramossi.payment.infra.delegate.ProductDelegate;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductDTO;
import br.com.renanrramossi.payment.interfaceadapter.dto.SaleDTO;
import br.com.renanrramossi.payment.interfaceadapter.mapper.ProductMapper;
import br.com.renanrramossi.payment.interfaceadapter.mapper.SaleMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController extends BaseController<ProductDTO> {

  private final ProductDelegate productDelegate;

  @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
      consumes = {"application/json", "application/xml", "application/x-yaml"})
  public ResponseEntity<ProductDTO> create(@NonNull @RequestBody final ProductForm productForm) {

    final ProductDTO productDTO = productDelegate.create(ProductMapper.INSTANCE.mapProductFrom(productForm));

    setSelfLink(productDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
  }

  @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
  public ProductDTO findById(@PathVariable("id") final Long id) {
    final ProductDTO productDTO = productDelegate.findById(id);
    setSelfLink(productDTO);
    return productDTO;
  }

  @Override
  protected void setSelfLink(final ProductDTO productDTO) {
    productDTO.add(linkTo(methodOn(ProductController.class)
        .findById(productDTO.getId())).withSelfRel());
  }
}
