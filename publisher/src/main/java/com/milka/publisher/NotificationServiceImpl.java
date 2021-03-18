package com.milka.publisher;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class NotificationServiceImpl implements NotificationService {

    public static final String URL_USER_SERVICE = "http://localhost:8082/users/";
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;

    public NotificationServiceImpl(RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public Notification sendUserNotification(long userId) {
        User user = restTemplate.exchange(URL_USER_SERVICE + userId, HttpMethod.GET, HttpEntity.EMPTY, User.class).getBody();
        Notification notification = new Notification();
        notification.setMessage("Zostało wysłane powiadomienie do :" + user.getName());
        notification.setName("email: " + user.getEmail());
        System.out.println(user.getEmail());
        System.out.println(notification.message);
        rabbitTemplate.convertAndSend(notification);
        return notification;
    }
}
