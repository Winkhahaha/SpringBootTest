package test.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

//         索引库名称              类型名            分片
@Document(indexName = "mineok3", type = "item", shards = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Field(type = FieldType.Long)
    @Id
    Long id;
                                    // 分词     分词类型
    @Field(type = FieldType.Text/*, analyzer = "ik_smart"*/)
    String title;           // 标题

    @Field(type = FieldType.Keyword)
    String category;        // 分类

    @Field(type = FieldType.Keyword)
    String brand;           // 品牌

    @Field(type = FieldType.Double)
    Double price;           // 价格
                                        // 不适用该字段检索数据
    @Field(type = FieldType.Keyword, index = false)
    String images;          // 图片地址

}