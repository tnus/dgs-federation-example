package com.example.demo.authors.graphql;

import com.example.demo.authors.graphql.generated.types.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureHttpGraphQlTester
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GraphAuthorsControllerTest {

    @Autowired
    private HttpGraphQlTester tester;

    @Test
    void verifiesAuthorsQuery() {
        String query = """
                query Entities($representations: [_Any!]!) {
                  _entities(representations: $representations) {
                    ...on Show {
                      id
                      authors {
                        name
                      }
                    }
                  }
                }
                """;

        tester.document(query).variable("representations", List.of(Map.of("__typename", "Show", "id", "4711"))).execute().path("_entities[0].id").entity(String.class).isEqualTo("4711").path("_entities[0].authors[0]").entity(Author.class).isEqualTo(
                Author.newBuilder().name("4711 - Harrison Ford").build());
    }
}