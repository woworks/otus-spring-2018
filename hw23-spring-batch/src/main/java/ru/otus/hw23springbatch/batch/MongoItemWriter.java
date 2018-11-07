package ru.otus.hw23springbatch.batch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import ru.otus.hw23springbatch.domain.Book;

import java.util.List;

@Component
public class MongoItemWriter implements ItemWriter<Book> {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoItemWriter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void write(final List<? extends Book> books) {
        for (Book book : books) {
            System.out.println("book to save in mongo = " + book);
            mongoTemplate.save(book, "books");
        }
    }
}