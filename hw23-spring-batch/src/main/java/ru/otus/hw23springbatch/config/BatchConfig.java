package ru.otus.hw23springbatch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.hw23springbatch.batch.MongoItemWriter;
import ru.otus.hw23springbatch.domain.Book;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

@EnableBatchProcessing
@Configuration
public class BatchConfig {
    private final Logger logger = LoggerFactory.getLogger(BatchConfig.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Bean
    public ItemReader<Book> reader() {
        JpaPagingItemReader<Book> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("select b from Book b");
        reader.setPageSize(5);
        return reader;
    }

    @Bean
    public ItemProcessor processor() {
        return new ItemProcessor<Book, Book>() {
            @Override
            public Book process(Book book) throws Exception {
                book.setTitle(book.getTitle() + ":" + LocalDateTime.now());
                return book;
            }
        };
    }

    @Bean
    MongoItemWriter writer(MongoTemplate mongoTemplate){
        return new MongoItemWriter(mongoTemplate);
    }

    @Bean
    public Job importBooksJob(Step step1) {
        return jobBuilderFactory.get("importBooksJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        logger.info("Начало job. Clearing up the mongo books..");
                        mongoTemplate.dropCollection("books");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        logger.info("Конец job");
                        logger.info("All the imported books:");
                        mongoTemplate.findAll(ru.otus.hw23springbatch.domain.mongo.MBook.class)
                                .forEach(book -> logger.info(book.toString()));
                    }
                })
                .build();
    }

    @Bean
    public Step step1(MongoItemWriter writer) {
        return stepBuilderFactory.get("step1")
                .chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .listener(new ItemReadListener() {
                    public void beforeRead() { logger.info("Начало чтения"); }
                    public void afterRead(Object o) { logger.info("Конец чтения"); }
                    public void onReadError(Exception e) { logger.info("Ошибка чтения"); }
                })
                .listener(new ItemWriteListener() {
                    public void beforeWrite(List list) { logger.info("Начало записи"); }
                    public void afterWrite(List list) { logger.info("Конец записи"); }
                    public void onWriteError(Exception e, List list) { logger.info("Ошибка записи"); }
                })
                .listener(new ItemProcessListener() {
                    public void beforeProcess(Object o) {logger.info("Начало обработки");}
                    public void afterProcess(Object o, Object o2) {logger.info("Конец обработки");}
                    public void onProcessError(Object o, Exception e) {logger.info("Ошбка обработки");}
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {logger.info("Начало пачки");}
                    public void afterChunk(ChunkContext chunkContext) {logger.info("Конец пачки");}
                    public void afterChunkError(ChunkContext chunkContext) {logger.info("Ошибка пачки");}
                })
                .build();
    }
}
