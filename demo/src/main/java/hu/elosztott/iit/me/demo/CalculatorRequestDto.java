package hu.elosztott.iit.me.demo;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CalculatorRequestDto {

  @NotNull
  private Double operandus1;
  @NotNull
  private Double operandus2;
  @NotNull
  private String operator;

}
