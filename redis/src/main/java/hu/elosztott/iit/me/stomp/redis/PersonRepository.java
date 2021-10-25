package hu.elosztott.iit.me.stomp.redis;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {
}
