package hu.elosztott.iit.me.stomp.redis;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("person")
public class PersonController {
  private final PersonRepository personRepository;

  @PostMapping(consumes = "application/json")
  public void savePerson(@RequestBody @Valid PersonDto personDto) {
    personRepository.save(personDto.toPerson());
  }

  @GetMapping("/{id}")
  public PersonDto getPerson(@PathVariable String id) {
    return new PersonDto(
        personRepository.findById(id).orElseThrow(EntityNotExistException::new));
  }
}
