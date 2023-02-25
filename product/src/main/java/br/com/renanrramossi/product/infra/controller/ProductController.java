package br.com.renanrramossi.product.infra.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.infra.delegate.ProductDelegate;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController {

  @NonNull
  private final ProductDelegate productDelegate;

  @PostMapping(value = "/products")
  public ProductDTO create(@NonNull @RequestBody final ProductForm productForm) {
    return productDelegate.create(productForm);
  }

  @GetMapping(value = "/products", produces = {"application/json", "application/xml", "application/x-yaml"})
  public Page<ProductDTO> findAll(final Pageable pageable) {
    return productDelegate.findAll(pageable);
  }

  public void delete(final Long productId) {
      productDelegate.delete(productId);
  }

  @GetMapping(value = "/products/{productId}", produces = {"application/json", "application/xml", "application/x-yaml"})
  public ProductDTO findById(@PathVariable("productId") final Long productId) {
    final ProductDTO productDTO = productDelegate.findById(productId);
    productDTO.add(linkTo(methodOn(ProductController.class)
        .findById(productId)).withSelfRel());
    return productDTO;
  }

  public ProductDTO update(final ProductForm productForm) {
    return productDelegate.update(productForm);
  }
}
