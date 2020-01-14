package test.task.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.service.AsyncService;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;
    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        asyncService.hello();
        return "success";
    }
}
