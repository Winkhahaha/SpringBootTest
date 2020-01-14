package config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.HashMap;

@ConfigurationProperties(prefix = "jdbc")
@Data
public class Config {
private  String driverClass;
private String url;
private String username;
private String password;

HashMap<String,String> ccc = new HashMap<>();
static class AAA{
    static int aa;
    static int bb;
}

}
