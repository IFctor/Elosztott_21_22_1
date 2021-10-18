package hu.elosztott.iit.me.elastic;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FindByDto {
  @NotBlank private String query;
}
