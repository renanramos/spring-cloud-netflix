package br.com.renanrramossi.product.infra.controller;

import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.infra.delegate.ProductDelegate;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/products")
public class ProductController extends BaseController {

  @NonNull
  private final ProductDelegate productDelegate;

  private final PagedResourcesAssembler<ProductDTO> assembler;

  @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
               consumes = {"application/json", "application/xml", "application/x-yaml"})
  public ResponseEntity<ProductDTO> create(@NonNull @RequestBody final ProductForm productForm) {

    final ProductDTO productDTO = productDelegate.create(productForm);

    setSelfLink(productDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
  }

  @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
  public ResponseEntity<PagedModel<EntityModel<ProductDTO>>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "limit", defaultValue = "10") int limit,
      @RequestParam(value = "direction", defaultValue = "asc") String direction) {

    final Pageable pageable = getPageable(page, limit, direction);

    final Page<ProductDTO> products = productDelegate.findAll(pageable);

    return new ResponseEntity<>(buildPagedModel(assembler, products), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{productId}")
  public ResponseEntity<Void> delete(@PathVariable("productId") final Long productId) {
      productDelegate.delete(productId);
      return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping(value = "/{productId}", produces = {"application/json", "application/xml", "application/x-yaml"})
  public ResponseEntity<ProductDTO> findById(@PathVariable("productId") final Long productId) {
    final ProductDTO productDTO = productDelegate.findById(productId);

    setSelfLink(productDTO);

    return ResponseEntity.status(HttpStatus.OK).body(productDTO);
  }

  @PutMapping(value = "/{productId}")
  public ResponseEntity<ProductDTO> update(@PathVariable("productId") final Long productId, @RequestBody final ProductForm productForm) {
    productForm.setId(productId);
    final ProductDTO productDTO = productDelegate.update(productForm);

    setSelfLink(productDTO);

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(productDTO);
  }
}
