package br.com.renanrramossi.auth.interfaceadapter.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({
    "username",
    "password"
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserForm implements Serializable {

  @JsonPropertyOrder("username")
  private String userName;

  @JsonPropertyOrder("password")
  private String password;

}
