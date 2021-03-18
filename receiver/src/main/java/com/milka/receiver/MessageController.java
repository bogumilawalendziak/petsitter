package com.milka.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final RabbitTemplate rabbitTemplate;


    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

   /* @GetMapping
    public String getMessage() {
        String message = rabbitTemplate.receiveAndConvert("kurs").toString();
        if (message.isEmpty()) {
            return "Nie ma wiadomości do odczytu";
        }
        return "Udało się pobrać wiadomość" + message;
    }*/

    @GetMapping
    public ResponseEntity<Notification> getNotification() {
        Notification notification = rabbitTemplate.receiveAndConvert("kurs", ParameterizedTypeReference.forType(Notification.class));
        if (notification != null) {
            return ResponseEntity.ok(notification);
        }
        return ResponseEntity.notFound().build();
    }

    @RabbitListener(queues = "kurs")
    public void listenerMessage(String message) {
        System.out.println(message);
    }

    @RabbitListener(queues = "kurs")
    public void listenerNotification(Notification message) {
        System.out.println(message.getMessage());
    }
}
