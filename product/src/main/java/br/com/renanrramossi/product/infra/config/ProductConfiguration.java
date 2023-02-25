package br.com.renanrramossi.product.infra.config;

import br.com.renanrramossi.product.core.usecase.ProductUseCaseImpl;
import br.com.renanrramossi.product.infra.delegate.ProductDelegateImpl;
import br.com.renanrramossi.product.interfaceadapter.gateway.ProductGatewayImpl;
import br.com.renanrramossi.product.interfaceadapter.repository.ProductRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ProductConfiguration {

  @NotNull
  private final ProductRepository productRepository;

  @Bean
  public ProductDelegateImpl productDelegate() {

    return new ProductDelegateImpl(
        new ProductUseCaseImpl(
            new ProductGatewayImpl(productRepository)));
  }
}
