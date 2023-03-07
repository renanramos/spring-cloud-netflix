package br.com.renanrramossi.product.infra.config.rabbitmq;

import br.com.renanrramossi.product.core.domain.Product;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSendMessage {

  @Value("${crud.rabbitmq.exchange}")
  private String exchange;

  @Value("${crud.rabbitmq.routingkey}")
  private String routingkey;

  @NonNull
  public final RabbitTemplate rabbitTemplate;

  public void sendMessage(final Product product) {
    rabbitTemplate.convertAndSend(exchange, routingkey, product);
  }
}
