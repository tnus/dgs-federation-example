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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
class GraphAuthorsController {

    @QueryMapping
    public List<Author> authors() {
        log.info("load authors");

        return List.of(createAuthor("Harrison Ford"), createAuthor("Pierce Brosnan"));
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
            resultMap.put(show, List.of(createAuthor(show.getId() + " - Harrison Ford"), createAuthor(show.getId() + " - Pierce Brosnan")));
        });

        return resultMap;
    }


    private Author createAuthor(String name) {
        return Author.newBuilder().name(name).build();
    }

}
