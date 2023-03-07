package br.com.renanrramossi.product.interfaceadapter.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import br.com.renanrramossi.product.core.domain.Product;
import br.com.renanrramossi.product.core.usecase.dto.ProductForm;
import br.com.renanrramossi.product.infra.config.rabbitmq.ProductSendMessage;
import br.com.renanrramossi.product.interfaceadapter.dto.ProductDTO;
import br.com.renanrramossi.product.interfaceadapter.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductGatewayImplTest {

  public static final long ID = 1L;
  public static final String PRODUCT_1 = "Product 1";
  public static final double PRECO = 20.00;
  public static final int ESTOQUE = 20;

  @Mock
  private ProductRepository mockProductRepository;

  @Mock
  private ProductSendMessage mockProductSendMessage;

  @InjectMocks
  private ProductGatewayImpl productGateway;

  @Test
  void create_withProductForm_shouldReturnProductDTO() {

    final Product product = getProduct();

    final ProductForm productForm = getProductForm();

    when(mockProductRepository.save(any())).thenReturn(product);

    final ProductDTO productDTO = productGateway.create(productForm);

    assertThat(productDTO).isNotNull();
    assertThat(productDTO.getId()).isEqualTo(product.getId());
    assertThat(productDTO.getNome()).isEqualTo(product.getName());
    assertThat(productDTO.getEstoque()).isEqualTo(product.getStock());
    assertThat(productDTO.getPreco()).isEqualTo(product.getPrice());
  }

  private Product getProduct() {
    return Product
        .builder()
        .id(ID)
        .name(PRODUCT_1)
        .price(PRECO)
        .stock(ESTOQUE)
        .build();
  }

  private ProductForm getProductForm() {
    return ProductForm
        .builder()
        .id(ID)
        .nome(PRODUCT_1)
        .preco(PRECO)
        .estoque(ESTOQUE)
        .build();
  }

  @Test
  void findAll() {
  }

  @Test
  void findById() {
  }

  @Test
  void update() {
  }

  @Test
  void delete() {
  }
}
