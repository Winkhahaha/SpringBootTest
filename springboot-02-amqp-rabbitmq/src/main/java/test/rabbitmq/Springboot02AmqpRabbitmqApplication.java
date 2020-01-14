package test.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * RabbitTemplate,给RabbitMQ发送和接收消息
 * Amqpadmin RabbitMQ系统管理功能组件
 * :创建和删除queue
 * @EnableRabbit + @RabbitListener 监听消息队列的内容
 */

@EnableRabbit   //开启基于注解的rabbitMQ
@SpringBootApplication
public class Springboot02AmqpRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02AmqpRabbitmqApplication.class, args);
    }

}
