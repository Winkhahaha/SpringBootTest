package test.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import test.elasticsearch.bean.Book;
import test.elasticsearch.entity.Item;

import java.util.List;

public interface ItemRepository extends ElasticsearchRepository<Item,Integer>{

    public List<Item> findItemByPriceBetween(Double start,Double end);
}
