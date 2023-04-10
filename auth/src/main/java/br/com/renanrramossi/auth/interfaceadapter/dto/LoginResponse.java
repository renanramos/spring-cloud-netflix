package br.com.renanrramossi.auth.interfaceadapter.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({
    "username",
    "token"
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LoginResponse implements Serializable {

  @JsonPropertyOrder("username")
  private String username;

  @JsonPropertyOrder("token")
  private String token;
}
