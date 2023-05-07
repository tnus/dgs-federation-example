package com.example.demo.authors.graphql;

import com.example.demo.authors.graphql.generated.types.Author;
import com.example.demo.authors.graphql.generated.types.Show;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
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
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
class GraphAuthorsController {

    private Map<String, List<Author>> showAuthors;

    public GraphAuthorsController() {
        showAuthors = new HashMap<>();
        showAuthors.put("1", List.of(createAuthor("Matt Duffer"), createAuthor("Ross Duffer"))); // Stranger Things
        showAuthors.put("2", List.of(createAuthor("Bill Dubuque"), createAuthor("Mark Williams"))); // Ozark
        showAuthors.put("3", List.of(createAuthor("Peter Morgan"))); // The Crown
        showAuthors.put("4", List.of(createAuthor("Liz Feldman"))); // Dead to Me
        showAuthors.put("5", List.of(createAuthor("Piper Kerman"))); // Orange is the new black
    }


    @QueryMapping
    public List<Author> authors() {
        log.info("load authors");

        Set<Author> allAuthors = new HashSet<>();

        showAuthors.values().stream().forEach(allAuthors::addAll);

        log.info("{} authors found", allAuthors.size());

        return  allAuthors.stream().toList();
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


    private Author createAuthor(String name) {
        return Author.newBuilder().name(name).build();
    }

}
