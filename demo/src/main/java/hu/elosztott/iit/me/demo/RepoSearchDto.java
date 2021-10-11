package hu.elosztott.iit.me.demo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RepoSearchDto {
  @NotNull
  @Length(min = 3)
  private String queryString;
}
