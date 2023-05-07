package com.example.demo.authors.graphql;

import com.example.demo.authors.graphql.generated.types.Author;
import com.example.demo.authors.graphql.generated.types.Show;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
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


    @QueryMapping
    public List<Author> authors() {
        log.info("load authors");

        //Set<Author> allAuthors = new HashSet<>();
//        showAuthors.values().stream().forEach(allAuthors::addAll);
        List<Author> authors = showAuthors.values().stream().flatMap(list -> list.stream()).distinct().collect(Collectors.toList());

        log.info("{} authors found", authors.size());

        return  authors.stream().toList();
    }

    @QueryMapping
    public Author author(@Argument String id) {
        log.info("load author with id {}", id);

        Optional<Author> optionalAuthor = showAuthors.values().stream().flatMap(list -> list.stream()).filter(author -> author.getId().equals(id)).findFirst();
        return optionalAuthor.orElse(null);
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
