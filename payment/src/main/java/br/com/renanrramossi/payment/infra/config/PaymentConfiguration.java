package br.com.renanrramossi.payment.infra.config;

import br.com.renanrramossi.payment.core.usecase.ProductUseCaseImpl;
import br.com.renanrramossi.payment.core.usecase.SaleUseCaseImpl;
import br.com.renanrramossi.payment.infra.delegate.ProductDelegateImpl;
import br.com.renanrramossi.payment.infra.delegate.SaleDelegateImpl;
import br.com.renanrramossi.payment.interfaceadapter.gateway.ProductGatewayImpl;
import br.com.renanrramossi.payment.interfaceadapter.gateway.SaleGatewayImpl;
import br.com.renanrramossi.payment.interfaceadapter.repository.ProductRepository;
import br.com.renanrramossi.payment.interfaceadapter.repository.ProductSaleRepository;
import br.com.renanrramossi.payment.interfaceadapter.repository.SaleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class PaymentConfiguration {

  @NonNull
  private final SaleRepository saleRepository;

  @NonNull
  private final ProductSaleRepository productSaleRepository;

  @NonNull
  private final ProductRepository productRepository;

  @Bean
  public SaleDelegateImpl saleDelegate() {
    return new SaleDelegateImpl(
        new SaleUseCaseImpl(
            new SaleGatewayImpl(saleRepository, productSaleRepository)
        )
    );
  }

  @Bean
  public ProductDelegateImpl productDelegate() {
    return new ProductDelegateImpl(
        new ProductUseCaseImpl(
            new ProductGatewayImpl(productRepository)
        )
    );
  }


}
