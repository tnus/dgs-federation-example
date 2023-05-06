package com.example.demo.authors.graphql;

import com.example.demo.authors.graphql.generated.Author;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
class GraphQLQueryController {

    @QueryMapping
    public List<Author> authors() {
        return List.of(createAuthor("Harrison Ford"), createAuthor("Pierce Brosnan"));
    }

    private Author createAuthor(String name) {
        Author author = new Author();
        author.setName(name);
        return author;
    }

}
