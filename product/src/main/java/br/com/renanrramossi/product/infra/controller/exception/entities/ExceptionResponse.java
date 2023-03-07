package br.com.renanrramossi.product.infra.controller.exception.entities;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

  private Date timestamp;
  private String message;
  private String details;

}
