package hu.elosztott.iit.me.demo.impl;

import hu.elosztott.iit.me.demo.Repo;
import hu.elosztott.iit.me.demo.github.GithubItemDto;
import hu.elosztott.iit.me.demo.github.GithubSearchResponseRoot;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GithubRepo implements Repo {

  private final String gitHubSearchRepositoryBaseUrl = "https://api.github.com/search/repositories";

  private final RestTemplate restTemplate;

  @Override
  public List<String> searchBytext(String queryString) {
    String url = gitHubSearchRepositoryBaseUrl + "?q=" + queryString;
    ResponseEntity<GithubSearchResponseRoot> response =
        restTemplate.getForEntity(url, GithubSearchResponseRoot.class);
    return response.getBody().getItems().stream()
        .map(GithubItemDto::getName)
        .collect(Collectors.toList());
  }
}
