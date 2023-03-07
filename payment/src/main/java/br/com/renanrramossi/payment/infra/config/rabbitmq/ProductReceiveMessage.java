package br.com.renanrramossi.payment.infra.config.rabbitMq;

import br.com.renanrramossi.payment.core.domain.Product;
import br.com.renanrramossi.payment.interfaceadapter.dto.ProductDTO;
import br.com.renanrramossi.payment.interfaceadapter.mapper.ProductMapper;
import br.com.renanrramossi.payment.interfaceadapter.repository.ProductRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductReceiveMessage {

  @NonNull
  private final ProductRepository productRepository;

  @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
  public void receiveMessage(final @Payload ProductDTO productDTO) {

    final Product product = ProductMapper.INSTANCE.mapProductFrom(productDTO);

    if (product != null) {
      productRepository.save(product);
    }
  }
}
