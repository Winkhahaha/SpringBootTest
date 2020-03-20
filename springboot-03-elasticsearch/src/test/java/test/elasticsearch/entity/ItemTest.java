package test.elasticsearch.entity;


import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import test.elasticsearch.repository.ItemRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ItemTest {

    @Resource
    ElasticsearchTemplate elasticsearchTemplate;
    @Resource
    ItemRepository itemRepository;

    @Test
    public void createIndexs() {
        // 创建索引库
        // 索引库名字,类型,文档,字段
        elasticsearchTemplate.createIndex(Item.class);
        // 创建映射
        elasticsearchTemplate.putMapping(Item.class);
    }

    @Test
    public void saveTest() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.taobao.com/123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.taobao.com/3.jpg")); // 接收对象集合，实现批量新增    
        list.add(new Item(4L, "苹果手机", " 手机", "苹果", 9999.00, "http://image.taobao.com/123.jpg"));
        itemRepository.saveAll(list);
    }

    @Test
    public void queryTest() {
        Iterable<Item> all = itemRepository.findAll();
        for (Item item : all) {
            System.out.println(item);
        }

    }

    @Test
    // 条件查询,可在接口中自定义查询语句
    public void queryBy() {
        List<Item> itemByPriceBetween = itemRepository.findItemByPriceBetween(1000D, 4000D);
        for (Item item : itemByPriceBetween) {
            System.out.println(item);
        }
    }

    // 原生查询
    @Test
    public void queryProperty() {
        // 按照标题中是否包含"手机"查询
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "手机");
        // 本地Search查询构建器(包装)
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        // 增加过滤条件:只要title,price,排除的字段数组为空
        searchQueryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"title", "price"}, null));
        // 按照价格降序排序
        searchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        // 分页
        searchQueryBuilder.withPageable(PageRequest.of(0, 2));
        // 创建附加条件的查询(包装)
        searchQueryBuilder.withQuery(queryBuilder);
        Iterable<Item> items = itemRepository.search(searchQueryBuilder.build());
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("总条数:" + ((Page<Item>) items).getTotalElements());
        System.out.println("总页数:" + ((Page<Item>) items).getTotalPages());
    }

    @Test
    public void queryProperty2() {
        // 按照标题中是否包含"手机"查询
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "手机");
        // 本地Search查询构建器(包装)
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        // 按品牌分组
        searchQueryBuilder.addAggregation(AggregationBuilders.terms("popularBrand").field("brand"));
        // 查询并返回聚合结果
        AggregatedPage<Item> items = elasticsearchTemplate.queryForPage(searchQueryBuilder.build(), Item.class);
        // 解析
        Aggregations aggregations = items.getAggregations();
        StringTerms popularBrand = aggregations.get("popularBrand");
        List<StringTerms.Bucket> buckets = popularBrand.getBuckets();
        // 遍历
        for (StringTerms.Bucket bucket : buckets) {
            System.out.println("品牌分组:" + bucket.getKeyAsString());
            System.out.println("该品牌分组下的商品数量:" + bucket.getDocCount());
        }
    }
}