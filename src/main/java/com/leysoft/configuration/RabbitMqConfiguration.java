
package com.leysoft.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitMqConfiguration {

    @Value(
            value = "${spring.rabbitmq.host}")
    private String host;

    @Value(
            value = "${spring.rabbitmq.port}")
    private int port;

    @Value(
            value = "${spring.rabbitmq.username}")
    private String username;

    @Value(
            value = "${spring.rabbitmq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
}
