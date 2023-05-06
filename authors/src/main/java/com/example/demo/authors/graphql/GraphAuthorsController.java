package com.example.demo.authors.graphql;

import com.example.demo.authors.graphql.generated.types.Author;
import com.example.demo.authors.graphql.generated.types.Show;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
class GraphAuthorsController {

    @QueryMapping
    public List<Author> authors() {
        log.info("load authors");

        return List.of(createAuthor("Harrison Ford"), createAuthor("Pierce Brosnan"));
    }

    @SchemaMapping(field = "authors", typeName = "Show")
//    @QueryMapping
    public List<Author> authorsByShow(Show show)    {
        log.info("load authors for show {}", show.getId());
        return List.of(createAuthor(show.getId() + " - Harrison Ford"), createAuthor(show.getId() + " - Pierce Brosnan"));
    }

    private Author createAuthor(String name) {
        return Author.newBuilder().name(name).build();
    }

}
