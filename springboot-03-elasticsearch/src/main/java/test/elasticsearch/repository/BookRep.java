package test.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import test.elasticsearch.bean.Book;

import java.util.List;

public interface BookRep extends ElasticsearchRepository<Book,Integer> {

    public List<Book> findByBookNameLike(String bookName);
}
