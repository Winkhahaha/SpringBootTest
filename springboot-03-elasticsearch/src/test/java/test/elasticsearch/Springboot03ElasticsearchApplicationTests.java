package test.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.elasticsearch.bean.Article;
import test.elasticsearch.bean.Book;
import test.elasticsearch.repository.BookRep;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class Springboot03ElasticsearchApplicationTests {

    @Autowired
    JestClient jestClient;

    @Test
    void contextLoads() {
        //给es索引中创建一个文档
        Article article = new Article();
        article.setId(1);
        article.setTitle("好消息");
        article.setAuthor("张三");
        article.setContent("hello world");

        // 构建一个索引功能
        Index index = new Index.Builder(article).index("test").type("news").build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void search() {
        String json = " \"query\":{        \"term\":{            \"content\":hello        }    }";
        Search search = new Search.Builder(json).addIndex("test").addType("news").build();
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Autowired
    BookRep bookRep;

    @Test
    public void repositoryTest(){
        Book book = new Book(1,"三国","罗贯中");
        bookRep.index(book);
    }

    @Test
    public void repositoryTest2(){
        //Book book = new Book(1,"三国","罗贯中");
        List<Book> books = bookRep.findByBookNameLike("三");
        System.out.println(books);
    }

}
