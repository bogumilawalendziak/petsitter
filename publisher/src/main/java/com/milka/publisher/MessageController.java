package com.milka.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class MessageController {
    private final RabbitTemplate rabbitTemplate;
    private final NotificationServiceImpl service;

    public MessageController(RabbitTemplate rabbitTemplate, RestTemplate restTemplate, NotificationServiceImpl service) {
        this.rabbitTemplate = rabbitTemplate;
        this.service = service;
    }

    @GetMapping("/message")
    public String sendMessage(@RequestParam String message) {

        rabbitTemplate.convertAndSend("kurs", message);
        return "Wrzucono wiadomość na RabbitMQ";
    }

    @GetMapping("/notifications/{userId}")
    public ResponseEntity<Notification> sendUserNotification(@PathVariable int userId) {
        Notification notification = service.sendUserNotification(userId);
        if (notification != null) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping("/notification")
    public String sendMessage(@RequestBody Notification notification) {

        rabbitTemplate.convertAndSend("kurs", notification);
        return "Wrzucono wiadomość na RabbitMQ";
    }
}
