package test.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot默认支持两种技术和ES交互
 * 1.Jest(默认不生效),需要导入Jest的工具包
 * 2.SpringData ElasticSearch:(es版本有可能不合适)
 * Client节点信息
 * ElasticSearchTemplate操作es
 * 编写ElasticSearchRepository的子接口来操作es:
 * interface BookRep extends ElasticsearchRepository<Book,Integer(主键类型)>
 */
@SpringBootApplication
public class Springboot03ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03ElasticsearchApplication.class, args);
    }

}
