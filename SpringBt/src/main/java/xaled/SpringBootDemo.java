package xaled;

import com.alibaba.druid.pool.DruidDataSource;
import config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
//@EnableConfigurationProperties(Config.class)
public class SpringBootDemo {
    public static void main(String args[]){

        SpringApplication.run(SpringBootDemo.class,args);
    }

    @Bean
    @ConfigurationProperties(prefix = "jdbc")
    public DataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
//        ds.setDriverClassName(jdbc.getDriverClass());
//        ds.setUsername(jdbc.getUsername());
//        ds.setPassword(jdbc.getPassword());
//        ds.setUrl(jdbc.getUrl());
        return ds;
    }

}
