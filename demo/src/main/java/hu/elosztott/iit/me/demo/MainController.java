package hu.elosztott.iit.me.demo;

import hu.elosztott.iit.me.demo.github.GithubSearchResponseRoot;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class MainController {

  private final Calculator calculator;
  private final Repo repo;

  @RequestMapping("/")
  String main() {
    return "Hello World!";
  }

  @RequestMapping(
      value = "/calculator",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  Double calculate(@Valid CalculatorRequestDto calculatorRequestDto) {
    return calculator.add(
        calculatorRequestDto.getOperandus1(), calculatorRequestDto.getOperandus2());
  }

  @RequestMapping(
      value = "/repoSearch",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  List<String> repoSearch(@Valid RepoSearchDto repoSearchDto) {
    return repo.searchBytext(repoSearchDto.getQueryString());
  }
}
