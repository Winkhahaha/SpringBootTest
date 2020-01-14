package test.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.rabbitmq.bean.Book;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Springboot02AmqpRabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    void createExchage(){
       // amqpAdmin.declareExchange(new DirectExchange("adqpadmin.exchange"));
        System.out.println("创建完成");

        //amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));

        // 创建exchange->queue绑定规则
        // amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"adqpadmin.exchange","amqp.哈哈",null));

    }

    /*
    单播:点对点
     */
    @Test
    void contextLoads() {
        // Message需要自己构造,可以定义消息内容和消息头
        //rabbitTemplate.send(exchage,routeKey,message);

        // 只需要传入要发送的对象,自动序列化发送给rabbitMQ
        //rabbitTemplate.convertAndSend(exchange,routeKey,object);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("hello",123,true));
        rabbitTemplate.convertAndSend("exchange.direct","test.news",new Book("好书","我写的"));

    }

    @Test
    void receiveTest() {
        // 接收数据
        Object o = rabbitTemplate.receiveAndConvert("test.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /*
    广播
     */
    @Test
    public void fanoutTest(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("大书","你的"));
    }

}
