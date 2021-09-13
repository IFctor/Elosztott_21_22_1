package hu.elosztott.iit.me.demo;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

  private final Calculator calculator;

  @RequestMapping("/")
  String main() {
    return "Hello World!";
  }

  @RequestMapping(value = "/calculator", produces = {MediaType.APPLICATION_JSON_VALUE})
  Double calculate(@Valid CalculatorRequestDto calculatorRequestDto) {
    return calculator
        .add(calculatorRequestDto.getOperandus1(), calculatorRequestDto.getOperandus2());
  }
}
