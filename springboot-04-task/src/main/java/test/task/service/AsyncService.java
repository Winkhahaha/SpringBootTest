package test.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AsyncService {

    @Async  //告诉spring这是一个异步方法
    public void hello() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("处理数据中...");

    }
}
