package ru.otus.hw23springbatch.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw23springbatch.domain.mongo.MBook;


@Repository
public interface MongoBookRepository extends MongoRepository<MBook, String> {
}
