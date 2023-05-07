package com.example.demo.authors.graphql;

import com.example.demo.authors.graphql.generated.types.Author;
import com.example.demo.authors.graphql.generated.types.AuthorSearchCriteria;
import com.example.demo.authors.graphql.generated.types.Show;
import com.graphql_java_generator.annotation.GraphQLInputType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
class GraphAuthorsController {

    private Map<String, List<Author>> showAuthors;

    public GraphAuthorsController() {
        showAuthors = new HashMap<>();
        showAuthors.put("1", List.of(createAuthor("A001","Matt", "Duffer"), createAuthor("A002", "Ross", "Duffer"))); // Stranger Things
        showAuthors.put("2", List.of(createAuthor("A003", "Bill", "Dubuque"), createAuthor("A004", "Mark", "Williams"))); // Ozark
        showAuthors.put("3", List.of(createAuthor("A005","Peter",  "Morgan"))); // The Crown
        showAuthors.put("4", List.of(createAuthor("A006","Liz", "Feldman"))); // Dead to Me
        showAuthors.put("5", List.of(createAuthor("A007","Piper", "Kerman"))); // Orange is the new black
    }


    @QueryMapping("author_findAll")
    public List<Author> findAllAuthors() {
        log.info("load authors");

        List<Author> authors = showAuthors.values().stream().flatMap(list -> list.stream()).distinct().collect(Collectors.toList());

        log.info("{} authors found", authors.size());

        return  authors.stream().toList();
    }

    @QueryMapping("author_findById")
    public Author findAuthorById(@Argument String id) {
        log.info("load author with id {}", id);

        Optional<Author> optionalAuthor = showAuthors.values().stream().flatMap(list -> list.stream()).filter(author ->  author.getId().equals(id)).findFirst();
        return optionalAuthor.orElse(null);
    }

    @QueryMapping("author_search")
    public List<Author> searchAuthors(@Argument AuthorSearchCriteria searchCriteria) {
        log.info("search authors with following search criteria: {}", searchCriteria);

        List<Author> authors = showAuthors.values().stream().flatMap(list -> list.stream()).filter(author -> {
            String searchCriteriaAuthorId = searchCriteria.getAuthorId();
            if (StringUtils.isNotBlank(searchCriteriaAuthorId)) {
                if (!author.getId().toLowerCase().contains(searchCriteriaAuthorId.toLowerCase())) {
                    return false;
                }
            }

            String searchCriteriaFirstName = searchCriteria.getFirstName();
            if (StringUtils.isNotBlank(searchCriteriaFirstName)) {
                if (!author.getFirstName().toLowerCase().contains(searchCriteriaFirstName.toLowerCase())) {
                    return false;
                }
            }

            String searchCriteriaLastName = searchCriteria.getLastName();
            if (StringUtils.isNotBlank(searchCriteriaLastName)) {
                if (!author.getLastName().toLowerCase().contains(searchCriteriaLastName.toLowerCase())) {
                    return false;
                }
            }

            return true;
        }).collect(Collectors.toList());

        log.info("{} authors found", authors.size());

        return  authors.stream().toList();
    }

    // replaced with batch mapping
//    @SchemaMapping(field = "authors", typeName = "Show")
//    public List<Author> authorsByShow(Show show) throws InterruptedException {
//        log.info("load authors for show {}", show.getId());
//
//        TimeUnit.MILLISECONDS.sleep(200);
//
//        return List.of(createAuthor(show.getId() + " - Harrison Ford"), createAuthor(show.getId() + " - Pierce Brosnan"));
//    }

    @BatchMapping(field = "authors", typeName = "Show")
    public Map<Show, List<Author>> authorsByShows(List<Show> shows) {
        log.info("load authors by shows {}", shows);

        Map<Show, List<Author>> resultMap = new HashMap<>();
        shows.forEach(show -> {
            resultMap.put(show, showAuthors.getOrDefault(show.getId(), Collections.emptyList()));
        });

        return resultMap;
    }


    private Author createAuthor(String id, String firstName, String lastName)
    {
        String fullName = firstName + " " + lastName;
        return Author.newBuilder().id(id).firstName(firstName).lastName(lastName).name(fullName).fullName(fullName).build();
    }

}
