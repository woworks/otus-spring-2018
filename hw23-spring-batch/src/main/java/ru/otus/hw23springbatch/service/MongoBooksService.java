package ru.otus.hw23springbatch.service;


import ru.otus.hw23springbatch.domain.mongo.MBook;

import java.util.List;

public interface MongoBooksService {
    List<MBook> getAll();

    void purge();
}
