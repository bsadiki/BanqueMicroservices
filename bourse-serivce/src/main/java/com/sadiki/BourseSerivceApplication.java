package com.sadiki;

import com.rabbitmq.client.*;
import com.sadiki.repository.SocieteRepository;
import com.sadiki.entities.Societe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

@EnableEurekaClient
@SpringBootApplication
public class BourseSerivceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BourseSerivceApplication.class, args);
        SocieteRepository societeRepository = context.getBean(SocieteRepository.class);
        Stream.of("A", "B", "C").forEach(s -> societeRepository.save(new Societe(s)));
        societeRepository.findAll().forEach(s -> System.out.println(s.getNomSociete()));
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare("Societe_queue", false, false, false, null);
            String message = "Societe details";
            channel.basicPublish("", "Societe_queue", null, message.getBytes());
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        factory.setPort(15678);
        factory.setUsername("user1");
        factory.setPassword("MyPassword");
    }

}