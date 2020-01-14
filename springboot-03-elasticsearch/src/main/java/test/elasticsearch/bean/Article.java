package test.elasticsearch.bean;


import io.searchbox.annotations.JestId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article {

    @JestId
    private Integer id;
    private String author;
    private String title;
    private String content;
}
