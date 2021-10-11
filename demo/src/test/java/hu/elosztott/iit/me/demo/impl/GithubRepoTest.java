package hu.elosztott.iit.me.demo.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hu.elosztott.iit.me.demo.RestCommunicationException;
import hu.elosztott.iit.me.demo.github.GithubItemDto;
import hu.elosztott.iit.me.demo.github.GithubSearchResponseRoot;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class GithubRepoTest {

  private static final String gitHubSearchRepositoryBaseUrl =
      "https://api.github.com/search/repositories";

  @Test
  void test_searchBytext_ok() throws RestCommunicationException {
    // GIVEN
    RestTemplate mockRestTemplate = mock(RestTemplate.class);
    GithubRepo repo = new GithubRepo(mockRestTemplate);
    String qs = "ize";

    GithubItemDto item = new GithubItemDto();
    item.setName("demo");
    List<GithubItemDto> itemList = new ArrayList<>();
    itemList.add(item);
    GithubSearchResponseRoot mockRoot = new GithubSearchResponseRoot();
    mockRoot.setItems(itemList);

    when(mockRestTemplate.getForEntity(
            gitHubSearchRepositoryBaseUrl + "?q=" + qs, GithubSearchResponseRoot.class))
        .thenReturn(new ResponseEntity<>(mockRoot, HttpStatus.OK));

    // WHEN
    List<String> response = repo.searchBytext(qs);
    // THEN
    assertThat(response, hasSize(1));
    assertThat(response.get(0), is("demo"));
    verify(mockRestTemplate)
        .getForEntity(gitHubSearchRepositoryBaseUrl + "?q=" + qs, GithubSearchResponseRoot.class);
  }

  @Test
  void test_searchBytext_404() {
    // GIVEN
    RestTemplate mockRestTemplate = mock(RestTemplate.class);
    GithubRepo repo = new GithubRepo(mockRestTemplate);
    String qs = "ize";

    when(mockRestTemplate.getForEntity(
            gitHubSearchRepositoryBaseUrl + "?q=" + qs, GithubSearchResponseRoot.class))
        .thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    // WHEN
    try {
      List<String> response = repo.searchBytext(qs);
      fail("Exception not thrown");
    } catch (RestCommunicationException e) {
      e.printStackTrace();
    }

    // THEN
    verify(mockRestTemplate)
        .getForEntity(gitHubSearchRepositoryBaseUrl + "?q=" + qs, GithubSearchResponseRoot.class);
  }
}
