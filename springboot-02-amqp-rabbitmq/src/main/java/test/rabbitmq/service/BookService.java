package test.rabbitmq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import test.rabbitmq.bean.Book;

@Service
public class BookService {

    @RabbitListener(queues = "test.news")
    public void receive(Book book){
        System.out.println("收到消息:"+book);
    }

    @RabbitListener(queues = "test")
    public void receive2(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
