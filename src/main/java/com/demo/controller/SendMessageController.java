package com.demo.controller;

import com.demo.service.MessageProducer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/message/")
public class SendMessageController {

    private final MessageProducer messageSender;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message) {
        messageSender.send(message);
        return "Message sent to the RabbitMQ";
    }
}
