package ru.otus.hw23springbatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw23springbatch.domain.mongo.MBook;
import ru.otus.hw23springbatch.repository.mongo.MongoBookRepository;

import java.util.List;

@Service
public class MongoBooksServiceImpl implements MongoBooksService {

    private MongoBookRepository mongoBookRepository;

    MongoBooksServiceImpl(@Autowired MongoBookRepository mongoBookRepository){
        this.mongoBookRepository = mongoBookRepository;
    }

    @Override
    public List<MBook> getAll() {
        return mongoBookRepository.findAll();
    }

    @Override
    public void purge() {
        mongoBookRepository.deleteAll();
    }
}
